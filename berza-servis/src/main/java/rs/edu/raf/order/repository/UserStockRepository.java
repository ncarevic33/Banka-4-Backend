package rs.edu.raf.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.order.model.Order;
import rs.edu.raf.order.model.UserStock;

import java.util.List;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Long> {

    List<UserStock> findAllByUserId(Long userId);

    UserStock findByUserIdAndTicker(Long userId, String ticker);

    void deleteByUserIdAndTicker(Long userId, String ticker);

}
