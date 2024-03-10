package rs.edu.raf.transakcija.dto;

import jakarta.persistence.Column;
import lombok.Data;
import rs.edu.raf.transakcija.model.Status;

import java.math.BigDecimal;

@Data
public class PrenosSredstavaDTO {

    private Long prviRacun;

    private Long drugiRacun;

    private BigDecimal iznos;

    private Long vreme;

    private Status status;

    private Long vremeIzvrsavanja;
}
