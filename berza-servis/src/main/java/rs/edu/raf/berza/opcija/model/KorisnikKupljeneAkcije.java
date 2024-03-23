package rs.edu.raf.berza.opcija.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class KorisnikKupljeneAkcije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long akcijaId;
    private Long korisnikId;

    @Enumerated(EnumType.STRING)
    private OpcijaTip opcijaTip;
    private long contractSize;//broj kupljenih/prodatih opcija
    private double strikePrice;//nezavisna cena kupljenih/prodatih akcija
    private double akcijaTickerTrenutnaCena;//stvarna cena akcija

}
