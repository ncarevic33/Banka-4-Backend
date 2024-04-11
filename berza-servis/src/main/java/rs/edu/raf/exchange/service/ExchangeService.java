package rs.edu.raf.exchange.service;

import rs.edu.raf.exchange.dto.ExchangeDTO;

import java.util.List;

public interface ExchangeService {
    ExchangeDTO getExchangeByExchangeName(String name);
    List<ExchangeDTO> getAllExchanges();
    List<ExchangeDTO> getExchangesByCurrency(String currency);
    List<ExchangeDTO> getOpenExchanges();
    List<ExchangeDTO> getExchangesByPolity(String polity);

}
