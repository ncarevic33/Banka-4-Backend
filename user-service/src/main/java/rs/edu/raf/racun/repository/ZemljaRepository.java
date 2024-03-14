package rs.edu.raf.racun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.racun.model.Zemlja;

@Repository
public interface ZemljaRepository extends JpaRepository<Zemlja, String> {

}
