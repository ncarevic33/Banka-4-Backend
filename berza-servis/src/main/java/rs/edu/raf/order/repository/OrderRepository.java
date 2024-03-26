package rs.edu.raf.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {



}
