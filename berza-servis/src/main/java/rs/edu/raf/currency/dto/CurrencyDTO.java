package rs.edu.raf.currency.dto;

import lombok.Data;

@Data
public class CurrencyDTO {

    private String currencyCode;

    private String currencyName;

    private String currencySymbol;

    private String polity;
}
