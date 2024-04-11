package rs.edu.raf.exchange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.ZoneId;
@Setter
@Getter
@NoArgsConstructor
public class ExchangeDTO {
    private String exchangeName;
    private String exchangeAcronym;
    private String exchangeMICCode;
    private String polity;
    private String currency;
    private String timeZone;

    public ExchangeDTO(String exchangeName) {
        this.exchangeName = exchangeName;
    }
}
