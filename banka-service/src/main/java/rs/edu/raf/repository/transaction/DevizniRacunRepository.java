package rs.edu.raf.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.edu.raf.model.entities.racun.DevizniRacun;

import java.util.Optional;

@Repository
public interface DevizniRacunRepository extends JpaRepository<DevizniRacun,Long> {


    //@Query("SELECT u FROM DevizniRacun u WHERE u.client.id = :clientId")
    //List<Object> findAllBillsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT dr.id FROM DevizniRacun dr ORDER BY dr.id DESC LIMIT 1")
    Long findTop1ByOrderByIdDesc(); //vraca najveci id

    Optional<DevizniRacun> findByIdAndAktivanIsTrue(Long id);

    Optional<DevizniRacun> findByBrojRacunaAndAktivanIsTrue(Long BrojRacuna);

    @Query("SELECT dr.vlasnik FROM DevizniRacun dr WHERE dr.brojRacuna = :brojRacuna AND dr.aktivan = true")
    Long findVlasnikByBrojRacuna(Long brojRacuna);
}
