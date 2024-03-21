package rs.edu.raf.model.dto.racun;

import lombok.Data;

import java.util.List;

@Data
public class NoviDevizniRacunDTO {

    private Long vlasnik; //korisnik id

    private Long zaposleni; //radnik id

    private List<String> currency; //nazivi valuta

    private String defaultCurrency; //naziv valute

    private Integer brojDozvoljenihValuta;
}
