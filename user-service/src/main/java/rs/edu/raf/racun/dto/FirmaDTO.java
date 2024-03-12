package rs.edu.raf.racun.dto;

import lombok.Data;

@Data
public class FirmaDTO {

    private String nazivPreduzeca;

    private String povezaniRacuni; //BrojRacuna

    private String brojTelefona;

    private String brojFaksa;

    private Integer PIB;

    private Integer maticniBroj;

    private Integer sifraDelatnosti;

    private Integer registarskiBroj;
}
