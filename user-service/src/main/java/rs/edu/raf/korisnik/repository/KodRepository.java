package rs.edu.raf.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.korisnik.model.Kod;

import java.util.Optional;

public interface KodRepository extends JpaRepository<Kod,Long> {
    Optional<Kod> findByEmailAndReset(String email, boolean reset);
}
