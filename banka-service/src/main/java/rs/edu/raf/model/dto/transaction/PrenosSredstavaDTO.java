package rs.edu.raf.model.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrenosSredstavaDTO {

    private Long prviRacun;

    private Long drugiRacun;

    private BigDecimal iznos;

    private Long vreme;

    private String status;

    private Long vremeIzvrsavanja;
}
