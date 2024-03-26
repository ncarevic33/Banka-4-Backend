package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;

import java.util.List;


public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();

    List<Order> getOrdersForUser(Long userId);
}
