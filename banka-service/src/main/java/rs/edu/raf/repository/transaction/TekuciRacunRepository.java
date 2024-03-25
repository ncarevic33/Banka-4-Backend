package rs.edu.raf.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.edu.raf.model.entities.racun.TekuciRacun;

import java.util.Optional;

@Repository
public interface TekuciRacunRepository extends JpaRepository<TekuciRacun,Long> {

    //@Query("SELECT u FROM TekuciRacun u WHERE u.client.id = :clientId")
    //List<Object> findAllBillsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT tr.id FROM TekuciRacun tr ORDER BY tr.id DESC LIMIT 1")
    Long findTop1ByOrderByIdDesc(); //vraca najveci id

    Optional<TekuciRacun> findByIdAndAktivanIsTrue(Long id);

    Optional<TekuciRacun> findByBrojRacunaAndAktivanIsTrue(Long BrojRacuna);

    @Query("SELECT tr.vlasnik FROM TekuciRacun tr WHERE tr.brojRacuna = :brojRacuna AND tr.aktivan = true")
    Long findVlasnikByBrojRacuna(Long brojRacuna);

}
