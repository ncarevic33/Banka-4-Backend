package rs.edu.raf.transakcija.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.Placanje;
import rs.edu.raf.transakcija.model.Status;

import java.util.List;


@Repository
public interface PlacanjaRepozitorijum extends JpaRepository<Placanje, Long> {

    List<Placanje> findAllByStatus(Status status);

    List<Placanje> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<Placanje> findAllByRacunPrimaoca(Long racunPrimaoca);

}
