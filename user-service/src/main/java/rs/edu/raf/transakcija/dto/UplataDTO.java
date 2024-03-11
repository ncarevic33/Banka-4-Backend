package rs.edu.raf.transakcija.dto;

import lombok.Data;
import rs.edu.raf.transakcija.model.Status;

import java.math.BigDecimal;

@Data
public class UplataDTO {

    private Long racunPosiljaoca;

    private String nazivPrimaoca;

    private Long racunPrimaoca;

    private BigDecimal iznos;

    private int pozivNaBroj;

    private int sifraPlacanja;

    private String svrhaPlacanja;

    private Status status;

    private Long vremeTransakcije;

    private Long vremeIzvrsavanja;
}
