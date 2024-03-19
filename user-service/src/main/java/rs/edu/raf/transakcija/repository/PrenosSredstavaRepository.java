package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.PrenosSredstava;

import java.util.List;

@Repository
public interface PrenosSredstavaRepository extends MongoRepository<PrenosSredstava, String> {

    List<PrenosSredstava> findAllByStatus(String status);

    List<PrenosSredstava> findAllByRacunPosiljaoca(Long racunPosiljaoca);

    List<PrenosSredstava> findAllByRacunPrimaoca(Long racunPrimaoca);

}
