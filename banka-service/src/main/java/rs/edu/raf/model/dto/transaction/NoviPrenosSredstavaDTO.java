package rs.edu.raf.model.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NoviPrenosSredstavaDTO {

    private Long racunPosiljaoca;

    private Long racunPrimaoca;

    private BigDecimal iznos;
}
