package rs.edu.raf.model.dto.racun;

import lombok.Data;

@Data
public class FirmaDTO {

    private Long id;

    private String nazivPreduzeca;

    private String povezaniRacuni; //BrojRacuna

    private String brojTelefona;

    private String brojFaksa;

    private Integer PIB;

    private Integer maticniBroj;

    private Integer sifraDelatnosti;

    private Integer registarskiBroj;
}
