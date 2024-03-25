package rs.edu.raf.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.model.entities.transaction.Uplata;

import java.util.List;


@Repository
public interface UplataRepository extends MongoRepository<Uplata, String> {

    List<Uplata> findAllByStatus(String status);

    List<Uplata> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<Uplata> findAllByRacunPrimaoca(Long racunPrimaoca);

}
