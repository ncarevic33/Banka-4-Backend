package rs.edu.raf.repository.racun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.model.entities.racun.Valute;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, String> {
}
