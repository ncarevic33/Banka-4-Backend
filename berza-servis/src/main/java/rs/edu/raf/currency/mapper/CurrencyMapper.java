package rs.edu.raf.currency.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.currency.dto.CurrencyDTO;
import rs.edu.raf.currency.model.Currency;

@Component
public class CurrencyMapper {
    public CurrencyDTO currencyToCurrencyDTO(Currency c) {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setCurrencyCode(c.getCurrencyCode());
        dto.setCurrencyName(c.getCurrencyName());
        dto.setCurrencySymbol(c.getCurrencySymbol());
        dto.setPolity(c.getPolity());
        return dto;
    }
}
