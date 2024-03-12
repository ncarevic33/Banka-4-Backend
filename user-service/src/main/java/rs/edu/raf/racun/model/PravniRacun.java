package rs.edu.raf.racun.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PravniRacun {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(unique = true)
    @NotBlank
    private Long brojRacuna;

    @NotNull
    private Long firma; // Firma id

    @NotNull
    private BigDecimal stanje;

    @NotNull
    private BigDecimal raspolozivoStanje;

    @NotNull
    private Long zaposleni; //Radnik id

    @NotNull
    private Long datumKreiranja;

    @NotNull
    private Long datumIsteka;

    @NotBlank
    private String currency;

    @NotNull
    private Boolean aktivan;
}

