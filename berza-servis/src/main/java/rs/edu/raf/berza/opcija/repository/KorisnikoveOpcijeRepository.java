package rs.edu.raf.berza.opcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.berza.opcija.model.KorisnikoveOpcije;

import java.util.Optional;

@Repository
public interface KorisnikoveOpcijeRepository extends JpaRepository<KorisnikoveOpcije,Long> {
    Optional<KorisnikoveOpcije> findFirstByOpcijaIdAndKorisnikId(Long opcijaId, Long korisnikId);
    void deleteFirstByOpcijaIdAndKorisnikId(Long opcijaId, Long korisnikId);
}
