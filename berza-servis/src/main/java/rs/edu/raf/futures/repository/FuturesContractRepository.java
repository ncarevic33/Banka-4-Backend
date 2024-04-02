package rs.edu.raf.futures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.futures.model.FuturesContract;

import java.util.List;

@Repository
public interface FuturesContractRepository extends JpaRepository<FuturesContract,Long> {
    List<FuturesContract> findAllByTypeAndKupacIdIsNull(String type);
    List<FuturesContract> findAllByNameAndKupacIdIsNull(String ticker);
    List<FuturesContract> findAllByKupacId(Long kupacId);
}
