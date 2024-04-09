package rs.edu.raf.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.currency.model.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    List<Currency> findAllByPolity(String polity);
}
