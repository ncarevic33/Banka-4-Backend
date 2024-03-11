package rs.edu.raf.transakcija.repository;

import jakarta.persistence.LockModeType;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.model.Status;

import java.util.List;


@Repository
public interface UplataRepository extends JpaRepository<Uplata, Long> {

    List<Uplata> findAllByStatus(String status);

    List<Uplata> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<Uplata> findAllByRacunPrimaoca(Long racunPrimaoca);

}
