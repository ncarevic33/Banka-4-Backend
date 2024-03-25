package rs.edu.raf.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.currency.model.Inflation;
import rs.edu.raf.currency.model.InflationId;

import java.util.List;

public interface InflationRepository extends JpaRepository<Inflation, InflationId> {
    List<Inflation> findAllByCurrency(String currencyCode);

    List<Inflation> findAllByCurrencyAndInflYear(String currencyCode, String year);
}
