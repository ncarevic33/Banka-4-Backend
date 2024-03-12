package rs.edu.raf.racun.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RacunDTO {

    private Long id;
    
    private Long brojRacuna;

    private Long vlasnik; //Korisnik ili Firma id

    private BigDecimal stanje;

    private BigDecimal raspolozivoStanje;

    private Long zaposleni; //Radnik id

    private Long datumKreiranja;

    private Long datumIsteka;

    private String currency;

    private Boolean aktivan;
}
