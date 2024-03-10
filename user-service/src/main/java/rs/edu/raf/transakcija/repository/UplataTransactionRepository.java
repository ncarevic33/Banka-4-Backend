package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;

@Repository
public interface UplataTransactionRepository extends JpaRepository<Uplata,Long> {


    //@Query("SELECT u FROM Uplata u WHERE u.bill.id = :billId")
    //List<Object> findAllUplataByBillId(@Param("billId") Long billId);

}
