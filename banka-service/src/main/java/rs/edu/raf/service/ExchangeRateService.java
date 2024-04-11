package rs.edu.raf.service;

import rs.edu.raf.model.dto.ExchangeRateResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {

    public List<ExchangeRateResponseDto> getAllExchangeRates();
    public BigDecimal convert(String oldValuteCurrencyCode, String newValuteCurrencyCode, BigDecimal oldValuteAmount);

}
