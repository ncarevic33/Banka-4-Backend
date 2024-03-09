package rs.edu.raf.transakcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.PrenosSredstava;

import java.util.List;

@Repository
public interface PrenosSredstavaTransactionRepository extends JpaRepository<PrenosSredstava,Long> {


    /*@Query("SELECT u FROM PrenosSredstava u WHERE u.bill.id = :billId")
    List<Object> findAllPrenosSredstavaByBillId(@Param("billId") Long billId);
*/
}
