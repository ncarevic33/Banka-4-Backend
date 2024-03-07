package rs.edu.raf.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.korisnik.model.Radnik;

@Repository
public interface RadnikRepository extends JpaRepository<Radnik, Long> {
}
