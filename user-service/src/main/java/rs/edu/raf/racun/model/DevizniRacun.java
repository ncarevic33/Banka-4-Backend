package rs.edu.raf.racun.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DevizniRacun {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long brojRacuna;

    @NotNull
    private Long vlasnik; //Korisnik id

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
    private String defaultCurrency;

    @NotNull
    private Boolean aktivan;

    @NotNull
    private BigDecimal kamatnaStopa; //procenat

    @NotNull
    private BigDecimal odrzavanjeRacuna;

    @NotNull
    private Integer brojDozvoljenihValuta;
}
