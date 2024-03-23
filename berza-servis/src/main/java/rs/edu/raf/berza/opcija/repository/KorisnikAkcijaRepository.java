package rs.edu.raf.berza.opcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.berza.opcija.model.KorisnikKupljeneAkcije;
@Repository
public interface KorisnikAkcijaRepository extends JpaRepository<KorisnikKupljeneAkcije,Long> {
}
