package rs.edu.raf.order.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.repository.OrderRepository;


public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);
}
