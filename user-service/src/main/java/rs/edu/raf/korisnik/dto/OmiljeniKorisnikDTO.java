package rs.edu.raf.korisnik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OmiljeniKorisnikDTO {

    private Long id;
    private Long idKorisnika;
    private Long idRacunaPosaljioca;
    private String nazivPrimaoca;
    private Long idRacunaPrimaoca;
    private Long broj;
    private String sifraPlacanja;
}
