package rs.edu.raf.model.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UplataDTO {

    private Long racunPosiljaoca;

    private String nazivPrimaoca;

    private Long racunPrimaoca;

    private BigDecimal iznos;

    private Integer pozivNaBroj;

    private Integer sifraPlacanja;

    private String svrhaPlacanja;

    private String status;

    private Long vremeTransakcije;

    private Long vremeIzvrsavanja;
}
