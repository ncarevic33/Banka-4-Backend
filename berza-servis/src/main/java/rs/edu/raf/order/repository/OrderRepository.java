package rs.edu.raf.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<OrderDto> findAllByUserId(Long userId);

    List<Order> findAllByAction(String action, String ticker);

}
