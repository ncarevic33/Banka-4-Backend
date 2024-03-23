package rs.edu.raf.berza.opcija.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class KorisnikoveOpcije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long korisnikId;

    private Long opcijaId;

}