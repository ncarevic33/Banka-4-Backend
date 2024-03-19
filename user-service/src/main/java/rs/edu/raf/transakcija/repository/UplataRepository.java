package rs.edu.raf.transakcija.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;

@Repository
public interface UplataRepository extends MongoRepository<Uplata, String> {

    List<Uplata> findAllByStatus(String status);

    List<Uplata> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<Uplata> findAllByRacunPrimaoca(Long racunPrimaoca);

}