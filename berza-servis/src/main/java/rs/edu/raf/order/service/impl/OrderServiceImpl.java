package rs.edu.raf.order.service.impl;

import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.repository.OrderRepository;
import rs.edu.raf.order.service.OrderService;

import java.util.List;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersForUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
