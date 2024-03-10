package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.racun.model.TekuciRacun;

import java.util.List;

@Repository
public interface TekuciRacunRepository extends JpaRepository<TekuciRacun,Long> {

    //@Query("SELECT u FROM TekuciRacun u WHERE u.client.id = :clientId")
    //List<Object> findAllBillsByClientId(@Param("clientId") Long clientId);


}
