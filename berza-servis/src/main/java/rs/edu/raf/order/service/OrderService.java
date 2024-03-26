package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;


public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);
}
