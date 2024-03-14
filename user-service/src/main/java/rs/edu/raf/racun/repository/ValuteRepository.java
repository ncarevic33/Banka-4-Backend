package rs.edu.raf.racun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.racun.model.Valute;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, String> {
}
