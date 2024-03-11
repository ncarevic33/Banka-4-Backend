package rs.edu.raf.transakcija.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "prenos_sredstava")
public class PrenosSredstava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long racunPosiljaoca;

    @Column(nullable = false)
    private Long racunPrimaoca;

    @Column(nullable = false)
    private BigDecimal iznos;

    @Column(nullable = false)
    private Long vreme;

    @Column(nullable = false)
    private String status;

    private Long vremeIzvrsavanja = null;

}
