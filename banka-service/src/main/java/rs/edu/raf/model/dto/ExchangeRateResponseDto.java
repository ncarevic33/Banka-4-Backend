package rs.edu.raf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ExchangeRateResponseDto {

    private String currencyCode;
    private BigDecimal rate;

}
