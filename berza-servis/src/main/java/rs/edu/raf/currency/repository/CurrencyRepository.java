package rs.edu.raf.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.currency.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
