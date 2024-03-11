package rs.edu.raf.transakcija.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.transakcija.model.SablonTransakcije;

@Repository
public interface SablonTransakcijeRepository extends JpaRepository<SablonTransakcije,Long> {


}
