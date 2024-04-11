package rs.edu.raf.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.edu.raf.exchange.model.Exchange;

import java.time.LocalTime;
import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange,Long> {
    List<Exchange> findAllByPolity(String polity);
    Exchange findByExchangeName(String name);
    List<Exchange> findAllByCurrency(String currency);

    @Query("SELECT e FROM Exchange e JOIN e.exchangeSchedule es " +
            "WHERE :currentTime BETWEEN es.openTime AND es.closeTime")
    List<Exchange> findOpenExchanges(String currentTime);

}
