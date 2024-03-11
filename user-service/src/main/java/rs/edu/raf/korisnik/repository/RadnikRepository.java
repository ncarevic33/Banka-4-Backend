package rs.edu.raf.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.korisnik.model.Radnik;

import java.util.List;
import java.util.Optional;

@Repository
public interface RadnikRepository extends JpaRepository<Radnik, Long> {
    Optional<Radnik> findByEmailAndAktivanIsTrue(String email);

    List<Radnik> findAllByAktivanIsTrue();
}
