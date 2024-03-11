package rs.edu.raf.transakcija.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.model.Status;

import java.util.List;


@Repository
public interface PlacanjaRepozitorijum extends JpaRepository<Uplata, Long> {

    List<Uplata> findAllByStatus(Status status);

    List<Uplata> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<Uplata> findAllByRacunPrimaoca(Long racunPrimaoca);

}
