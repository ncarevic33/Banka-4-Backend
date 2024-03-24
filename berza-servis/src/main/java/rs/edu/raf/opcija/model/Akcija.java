package rs.edu.raf.opcija.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Akcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticker;

    //PREDEFINISANO JER NEMA NA API
    private double akcijaTickerTrenutnaCena = 60;//menja se
    private long ukupanBrojIzdatihAkcijaKompanije = 1000;

}
