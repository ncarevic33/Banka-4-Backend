package rs.edu.raf.korisnik.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "OMILJENI_KORISNIK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmiljeniKorisnik implements Serializable {
    @Serial
    private static final long serialVersionUID = -6964392219393560447L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ID_KORISNIKA")
    private Long idKorisnika;
    @Column(name = "ID_RACUNA_POSALJIOCA")
    private Long idRacunaPosaljioca;
    @Column(name = "NAZIV_PRIMAOCA")
    private String nazivPrimaoca;
    @Column(name = "ID_RACUNA_PRIMAOCA")
    private Long idRacunaPrimaoca;
    private Long broj;
    @Column(name = "SIFRA_PLACANJA")
    private String sifraPlacanja;

}
