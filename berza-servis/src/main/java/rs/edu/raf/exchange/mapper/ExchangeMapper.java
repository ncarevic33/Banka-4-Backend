package rs.edu.raf.exchange.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import rs.edu.raf.exchange.dto.ExchangeDTO;
import rs.edu.raf.exchange.model.Exchange;
@Component
@AllArgsConstructor
public class ExchangeMapper {
    public ExchangeDTO exchangeToExchangeDTO(Exchange exchange){
        ExchangeDTO dto = new ExchangeDTO();
        dto.setExchangeName(exchange.getExchangeName());
        dto.setExchangeAcronym(exchange.getExchangeAcronym());
        dto.setExchangeMICCode(exchange.getExchangeMICCode());
        dto.setPolity(exchange.getPolity());
        dto.setCurrency(exchange.getCurrency());
        dto.setTimeZone(exchange.getTimeZone().getId()); // Pretvaranje ZoneId u String

        return dto;
    }
}
