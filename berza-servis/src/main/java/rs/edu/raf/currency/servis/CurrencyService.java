package rs.edu.raf.currency.servis;

import rs.edu.raf.currency.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    CurrencyDTO getCurrencyByCurrencyCode(String currencyCode);
    List<CurrencyDTO> getAllCurrencies();

    List<CurrencyDTO> getCurrenciesInPolity(String polity);

}
