package rs.edu.raf.korisnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.korisnik.model.Korisnik;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findByEmailAndAktivanIsTrue(String email);

    Optional<Korisnik> findByBrojTelefonaAndAktivanIsTrue(String brojTelefona);

    Optional<Korisnik> findByJmbgAndAktivanIsTrue(Long jmbg);

    List<Korisnik> findAllByAktivanIsTrue();
}
