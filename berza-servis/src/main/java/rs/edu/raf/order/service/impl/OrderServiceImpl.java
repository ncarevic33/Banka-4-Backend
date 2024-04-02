package rs.edu.raf.order.service.impl;

import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Enums.Action;
import rs.edu.raf.order.model.Enums.Type;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.repository.OrderRepository;
import rs.edu.raf.order.service.OrderService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto placeOrder(OrderRequest orderRequest) {

        return (orderRequest.getAction().equals(Action.BUY)) ? placeBuyOrder(OrderMapper.mapOrderRequestToOrder(orderRequest)) : placeSellOrder(OrderMapper.mapOrderRequestToOrder(orderRequest));
    }

    private OrderDto placeBuyOrder(Order buyOrder) {
        List<Order> sellOrders = findAllSellOrdersForTicker(buyOrder.getTicker());

        orderRepository.save(buyOrder);
        checkStopOrderAndStopLimitOrder();


        // check if enough money

        switch(buyOrder.getType()) {

            case Type.MARKET_ORDER -> {
                for (Order sellOrder : sellOrders) {
                    if (buyOrder.getQuantity() == 0) break;
                    executeBuyOrder(buyOrder, sellOrder);
                }
            }

            case Type.LIMIT_ORDER -> {
                for (Order sellOrder : sellOrders) {
                    if (buyOrder.getQuantity() == 0 || buyOrder.getLimit().compareTo(sellOrder.getLimit()) < 0) break;
                    executeBuyOrder(buyOrder, sellOrder);
                }
            }

        }

        return null;
    }

    private OrderDto placeSellOrder(Order sellOrder) {
        List<Order> buyOrders = findAllBuyOrdersForTicker(sellOrder.getTicker());

        orderRepository.save(sellOrder);
        checkStopOrderAndStopLimitOrder();

        switch(sellOrder.getType()) {

            case Type.MARKET_ORDER -> {
                for (Order buyOrder : buyOrders) {
                    if (sellOrder.getQuantity() == 0) break;
                    executeSellOrder(sellOrder, buyOrder);
                }
            }

            case Type.LIMIT_ORDER -> {
                for (Order buyOrder : buyOrders) {
                    if (sellOrder.getQuantity() == 0 || sellOrder.getLimit().compareTo(buyOrder.getLimit()) > 0) break;
                    executeSellOrder(sellOrder, buyOrder);
                }
            }

        }

        return null;
    }

    @Override
    public BigDecimal approximateOrderValue(OrderRequest orderRequest) {
        Order buyOrder = OrderMapper.mapOrderRequestToOrder(orderRequest);
        List<Order> sellOrders = findAllSellOrdersForTicker(buyOrder.getTicker());
        BigDecimal approximateValue = BigDecimal.ZERO;
        int remainingQuantity = buyOrder.getQuantity();

        switch(buyOrder.getType()) {

            case Type.MARKET_ORDER -> {
                for (Order sellOrder : sellOrders) {
                    if (remainingQuantity <= 0) break;

                    int quantityToUse = Math.min(remainingQuantity, sellOrder.getQuantity());
                    approximateValue = approximateValue.add(sellOrder.getLimit().multiply(new BigDecimal(quantityToUse)));
                    remainingQuantity -= quantityToUse;
                }
            }

            case Type.LIMIT_ORDER -> {
                for (Order sellOrder : sellOrders) {
                    if (remainingQuantity <= 0 || sellOrder.getLimit().compareTo(buyOrder.getLimit()) > 0) break;

                    int quantityToUse = Math.min(remainingQuantity, sellOrder.getQuantity());
                    approximateValue = approximateValue.add(sellOrder.getLimit().multiply(new BigDecimal(quantityToUse)));
                    remainingQuantity -= quantityToUse;
                }
                approximateValue = approximateValue.add(buyOrder.getLimit().multiply(new BigDecimal(remainingQuantity)));
            }

            case Type.STOP_ORDER, Type.STOP_LIMIT_ORDER -> approximateValue = approximateValue.add(buyOrder.getStop().multiply(new BigDecimal(remainingQuantity)).multiply(new BigDecimal("1.02")));

        }

        return approximateValue;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getOrdersForUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    private void executeBuyOrder(Order buyOrder, Order sellOrder) {
        if (sellOrder.getQuantity() > buyOrder.getQuantity()) {
            sellOrder.setQuantity(sellOrder.getQuantity() - buyOrder.getQuantity());

            orderRepository.save(sellOrder);
            orderRepository.delete(buyOrder);

            BigDecimal valueChange = sellOrder.getLimit().multiply(new BigDecimal(buyOrder.getQuantity()));
            modifyUserBalance(buyOrder.getUserId(), valueChange.negate());
            modifyUserBalance(sellOrder.getUserId(), valueChange);

        } else {
            buyOrder.setQuantity(buyOrder.getQuantity() - sellOrder.getQuantity());

            BigDecimal valueChange = sellOrder.getLimit().multiply(new BigDecimal(sellOrder.getQuantity()));
            modifyUserBalance(buyOrder.getUserId(), valueChange.negate());
            modifyUserBalance(sellOrder.getUserId(), valueChange);

            orderRepository.delete(sellOrder);
            if (buyOrder.getQuantity() == 0) orderRepository.delete(buyOrder);
            else orderRepository.save(buyOrder);
        }
    }

    private void executeSellOrder(Order sellOrder, Order buyOrder) {
        if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
            buyOrder.setQuantity(buyOrder.getQuantity() - sellOrder.getQuantity());

            orderRepository.save(buyOrder);
            orderRepository.delete(sellOrder);

            BigDecimal valueChange = buyOrder.getLimit().multiply(new BigDecimal(sellOrder.getQuantity()));
            modifyUserBalance(buyOrder.getUserId(), valueChange.negate());
            modifyUserBalance(sellOrder.getUserId(), valueChange);

        } else {
            sellOrder.setQuantity(sellOrder.getQuantity() - buyOrder.getQuantity());

            BigDecimal valueChange = buyOrder.getLimit().multiply(new BigDecimal(buyOrder.getQuantity()));
            modifyUserBalance(buyOrder.getUserId(), valueChange.negate());
            modifyUserBalance(sellOrder.getUserId(), valueChange);

            orderRepository.delete(buyOrder);
            if (sellOrder.getQuantity() == 0) orderRepository.delete(sellOrder);
            else orderRepository.save(sellOrder);
        }
    }

    private void checkStopOrderAndStopLimitOrder() {
        List<Order> allOrders = orderRepository.findAll();
        for (Order order : allOrders) {
            if (order.getType().equals(Type.STOP_ORDER)) {
                if (order.getAction().equals(Action.BUY)) {
                    List<Order> sellOrders = findAllSellOrdersForTicker(order.getTicker());
                    if (!sellOrders.isEmpty() && sellOrders.get(0).getLimit().compareTo(order.getStop()) >= 0) {
                        order.setType(Type.MARKET_ORDER);
                        placeBuyOrder(order);
                    }
                } else if (order.getAction().equals(Action.SELL)) {
                    List<Order> buyOrders = findAllBuyOrdersForTicker(order.getTicker());
                    if (!buyOrders.isEmpty() && buyOrders.get(0).getLimit().compareTo(order.getStop()) <= 0) {
                        order.setType(Type.MARKET_ORDER);
                        placeSellOrder(order);
                    }
                }
            } else if (order.getType().equals(Type.STOP_LIMIT_ORDER)) {
                if (order.getAction().equals(Action.BUY)) {
                    List<Order> sellOrders = findAllSellOrdersForTicker(order.getTicker());
                    if (!sellOrders.isEmpty() && sellOrders.get(0).getLimit().compareTo(order.getStop()) >= 0) {
                        order.setType(Type.LIMIT_ORDER);
                        placeBuyOrder(order);
                    }
                } else if (order.getAction().equals(Action.SELL)) {
                    List<Order> buyOrders = findAllBuyOrdersForTicker(order.getTicker());
                    if (!buyOrders.isEmpty() && buyOrders.get(0).getLimit().compareTo(order.getStop()) <= 0) {
                        order.setType(Type.LIMIT_ORDER);
                        placeSellOrder(order);
                    }
                }
            }
        }
    }

    private List<Order> findAllBuyOrdersForTicker(String ticker) {
        return orderRepository.findAllByActionAndTicker(Action.BUY, ticker)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit).reversed())
                .toList();
    }

    private List<Order> findAllSellOrdersForTicker(String ticker) {
        return orderRepository.findAllByActionAndTicker(Action.SELL, ticker)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit))
                .toList();
    }

    private String nadjiVrstuRacuna(Long BrojRacuna) {
        if (BrojRacuna % 100 == 11) return "DevizniRacun";
        if (BrojRacuna % 100 == 22) return "PravniRacun";
        if (BrojRacuna % 100 == 33) return "TekuciRacun";
        return null;
    }

    private void modifyUserBalance(Long userId, BigDecimal valueChange) {

        String userServiceUrl = "http://localhost:8080/user-service/users/" + userId + "/balance";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<BigDecimal> request = new HttpEntity<>(valueChange);
        restTemplate.put(userServiceUrl, request);
    }

}
