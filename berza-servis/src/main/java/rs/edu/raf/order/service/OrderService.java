package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;

import java.util.List;


public interface OrderService {

    OrderDto placeOrder(OrderRequest orderRequest);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersForUser(Long userId);
}
