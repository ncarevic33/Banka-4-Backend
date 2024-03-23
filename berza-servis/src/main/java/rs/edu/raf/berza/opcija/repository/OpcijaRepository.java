package rs.edu.raf.berza.opcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.berza.opcija.model.Opcija;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OpcijaRepository extends JpaRepository<Opcija,Long> {

    //@Query(value = "select * from Opcija as o where o.ticker like CONCAT('%',:stockSymbol ,'%') and o.datumIstekaVazenja = :expiryDate and o.strikePrice = :strikePrice limit :pageOffset,6",nativeQuery = true)
    /*@Query("SELECT o FROM Opcija o WHERE " +
            "o.ticker = :stockSymbol AND " +
            "o.datumIstekaVazenja = :expiryDate AND " +
            "o.strikePrice = :strikePrice")
            */
    @Query("SELECT o FROM Opcija o " +
            "WHERE (:stockSymbol IS NULL OR o.ticker = :stockSymbol) " +
            "AND (:expiryDate IS NULL OR o.datumIstekaVazenja = :expiryDate) " +
            "AND (:strikePrice IS NULL OR o.strikePrice = :strikePrice)")
    List<Opcija> findByStockAndDateAndStrike(@Param("stockSymbol") String ticker,
                                             @Param("expiryDate") LocalDateTime expiryDate,
                                             @Param("strikePrice") Double strikePrice);
                                             //@Param("pageOffset") int pageOffset);


}
