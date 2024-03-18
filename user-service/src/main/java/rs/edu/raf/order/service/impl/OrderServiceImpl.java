package rs.edu.raf.order.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import rs.edu.raf.order.repository.OrderRepository;
import rs.edu.raf.order.service.OrderService;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


}
