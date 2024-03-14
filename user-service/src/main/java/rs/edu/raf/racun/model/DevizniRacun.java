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

    public DevizniRacun(Long brojRacuna, Long vlasnik, BigDecimal stanje, BigDecimal raspolozivoStanje, Long zaposleni, Long datumKreiranja, Long datumIsteka, String currency, String defaultCurrency, Boolean aktivan, BigDecimal kamatnaStopa, BigDecimal odrzavanjeRacuna, Integer brojDozvoljenihValuta) {
        this.brojRacuna = brojRacuna;
        this.vlasnik = vlasnik;
        this.stanje = stanje;
        this.raspolozivoStanje = raspolozivoStanje;
        this.zaposleni = zaposleni;
        this.datumKreiranja = datumKreiranja;
        this.datumIsteka = datumIsteka;
        this.currency = currency;
        this.defaultCurrency = defaultCurrency;
        this.aktivan = aktivan;
        this.kamatnaStopa = kamatnaStopa;
        this.odrzavanjeRacuna = odrzavanjeRacuna;
        this.brojDozvoljenihValuta = brojDozvoljenihValuta;
    }

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
