package rs.edu.raf.berza.opcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.berza.opcija.model.Akcija;

import java.util.List;
@Repository
public interface AkcijaRepository extends JpaRepository<Akcija,Long> {
    Akcija findFirstByTicker(String ticker);
}
