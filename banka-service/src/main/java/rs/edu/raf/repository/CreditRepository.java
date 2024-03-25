package rs.edu.raf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.model.entities.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findCreditByCreditRequestId(Long creditRequestId);


}
