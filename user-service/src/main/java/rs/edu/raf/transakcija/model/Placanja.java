package rs.edu.raf.transakcija.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "placanja")
public class Placanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long racunPosiljaoca;

    @Column(nullable = false)
    private String nazivPrimaoca;

    @Column(nullable = false)
    private Long racunPrimaoca;

    @Column(nullable = false)
    private BigDecimal iznos;

    private int pozivNaBroj;

    private int sifraPlacanja;

    private String svrhaPlacanja;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Long vremeTransakcije;

    private Long vremeIzvrsavanja;

}
