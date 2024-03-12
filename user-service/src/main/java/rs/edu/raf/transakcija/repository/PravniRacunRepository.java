package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.racun.model.PravniRacun;

import java.util.List;
import java.util.Optional;

@Repository
public interface PravniRacunRepository extends JpaRepository<PravniRacun,Long> {


    //@Query("SELECT u FROM PravniRacun u WHERE u.client.id = :clientId")
    //List<Object> findAllBillsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT pr.id FROM PravniRacun pr ORDER BY pr.id DESC LIMIT 1")
    Long findTop1ByOrderByIdDesc(); //vraca najveci id

    Optional<PravniRacun> findByIdAndAktivanIsTrue(Long id);

    Optional<PravniRacun> findByBrojRacunaAndAktivanIsTrue(Long BrojRacuna);
}
