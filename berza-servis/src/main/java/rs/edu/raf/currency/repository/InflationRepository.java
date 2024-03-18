package rs.edu.raf.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.currency.model.Inflation;
import rs.edu.raf.currency.model.InflationId;

public interface InflationRepository extends JpaRepository<Inflation, InflationId> {
}
