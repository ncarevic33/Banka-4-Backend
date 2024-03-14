package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.racun.model.DevizniRacun;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevizniRacunRepository extends JpaRepository<DevizniRacun,Long> {


    //@Query("SELECT u FROM DevizniRacun u WHERE u.client.id = :clientId")
    //List<Object> findAllBillsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT dr.id FROM DevizniRacun dr ORDER BY dr.id DESC LIMIT 1")
    Long findTop1ByOrderByIdDesc(); //vraca najveci id

    Optional<DevizniRacun> findByIdAndAktivanIsTrue(Long id);

    Optional<DevizniRacun> findByBrojRacunaAndAktivanIsTrue(Long BrojRacuna);
}
