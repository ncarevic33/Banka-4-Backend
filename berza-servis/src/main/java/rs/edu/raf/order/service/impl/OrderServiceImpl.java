package rs.edu.raf.order.service.impl;

import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.dto.UserDto;
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
        List<Order> sellOrders = findAllSellOrders(buyOrder.getTicker());

        orderRepository.save(buyOrder);

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

            case Type.STOP_ORDER -> {

            }

            case Type.STOP_LIMIT_ORDER -> {

            }

        }

        return null;
    }



    private OrderDto placeSellOrder(Order sellOrder) {
        List<Order> buyOrders = findAllBuyOrders(sellOrder.getTicker());

        switch(sellOrder.getType()) {

            case Type.MARKET_ORDER -> {

            }

            case Type.LIMIT_ORDER -> {

            }

            case Type.STOP_ORDER -> {

            }

            case Type.STOP_LIMIT_ORDER -> {

            }

        }

        return null;
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

    @Override
    public BigDecimal approximateOrderValue(OrderRequest orderRequest) {
        Order buyOrder = OrderMapper.mapOrderRequestToOrder(orderRequest);
        List<Order> sellOrders = findAllSellOrders(buyOrder.getTicker());
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

            case Type.STOP_ORDER -> {
                // Implement logic for STOP_ORDER
            }

            case Type.STOP_LIMIT_ORDER -> {
                // Implement logic for STOP_LIMIT_ORDER
            }

        }

        return approximateValue;
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

    private List<Order> findAllBuyOrders(String ticker) {
        return orderRepository.findAllByAction(Action.BUY, ticker)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit).reversed()) // sort by highest first
                .toList();
    }

    private List<Order> findAllSellOrders(String ticker) {
        return orderRepository.findAllByAction(Action.SELL, ticker)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit)) // sort by lowest first
                .toList();
    }

    private String nadjiVrstuRacuna(Long BrojRacuna) {
        if (BrojRacuna % 100 == 11) return "DevizniRacun";
        if (BrojRacuna % 100 == 22) return "PravniRacun";
        if (BrojRacuna % 100 == 33) return "TekuciRacun";
        return null;
    }

    private void modifyUserBalance(Long userId, BigDecimal valueChange) {
        // Call the user service to modify the user's balance by valueChange
        String userServiceUrl = "http://localhost:8080/user-service/users/" + userId + "/balance";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<BigDecimal> request = new HttpEntity<>(valueChange);
        restTemplate.put(userServiceUrl, request);
    }

}
