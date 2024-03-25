package rs.edu.raf.model.mapper;


import org.springframework.stereotype.Component;
import rs.edu.raf.model.dto.ExchangeRateResponseDto;
import rs.edu.raf.model.entities.ExchangeRate;

@Component
public class ExchangeRateMapper {

    public ExchangeRateResponseDto exchangeRateToExchangeRateResponseDto(ExchangeRate exchangeRate){

        ExchangeRateResponseDto exchangeRateResponseDto = new ExchangeRateResponseDto();
        exchangeRateResponseDto.setCurrencyCode(exchangeRate.getCurrencyCode());
        exchangeRateResponseDto.setRate(exchangeRate.getRate());

        return exchangeRateResponseDto;
    }


}
