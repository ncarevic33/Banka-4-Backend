package rs.edu.raf.order.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.dto.UserDto;
import rs.edu.raf.order.model.Enums.Action;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.repository.OrderRepository;
import rs.edu.raf.order.service.OrderService;

import java.util.Comparator;
import java.util.List;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto placeOrder(OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String userServiceUrl = "http://localhost:8080/user-service/users/" + orderRequest.getUserId();
        UserDto userDto = restTemplate.getForObject(userServiceUrl, UserDto.class);

        if (userDto == null) return null;

        return (orderRequest.getAction().equals(Action.BUY)) ? placeBuyOrder(orderRequest, userDto) : placeSellOrder(orderRequest, userDto);
    }

    private OrderDto placeBuyOrder(OrderRequest orderRequest, UserDto userDto) {


        return null;
    }

    private OrderDto placeSellOrder(OrderRequest orderRequest, UserDto userDto) {


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

    private List<Order> findAllBuyOrders() {
        return orderRepository.findAllByAction(Action.BUY)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit))
                .toList();
    }

    private List<Order> findAllSellOrders() {
        return orderRepository.findAllByAction(Action.SELL)
                .stream()
                .sorted(Comparator.comparing(Order::getLimit).reversed())
                .toList();
    }

    private String nadjiVrstuRacuna(Long BrojRacuna) {
        if (BrojRacuna % 100 == 11) return "DevizniRacun";
        if (BrojRacuna % 100 == 22) return "PravniRacun";
        if (BrojRacuna % 100 == 33) return "TekuciRacun";
        return null;
    }

}
