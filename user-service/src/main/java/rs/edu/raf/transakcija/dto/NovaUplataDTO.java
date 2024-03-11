package rs.edu.raf.transakcija.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NovaUplataDTO {
    private Long racunPosiljaoca;

    private String nazivPrimaoca;

    private Long racunPrimaoca;

    private BigDecimal iznos;

    private Integer pozivNaBroj;

    private Integer sifraPlacanja;

    private String svrhaPlacanja;
}
