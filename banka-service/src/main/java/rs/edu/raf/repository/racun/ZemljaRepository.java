package rs.edu.raf.repository.racun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.model.entities.racun.Zemlja;

@Repository
public interface ZemljaRepository extends JpaRepository<Zemlja, String> {

}
