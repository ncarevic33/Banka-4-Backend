package rs.edu.raf.berza.opcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.berza.opcija.model.Akcija;

import java.util.List;
import java.util.Optional;

@Repository
public interface AkcijaRepository extends JpaRepository<Akcija,Long> {
    Optional<Akcija> findFirstByTicker(String ticker);
}
