package rs.edu.raf.currency.dto;

import lombok.Data;

@Data
public class InflationDTO {

    private String country;

    private String year;

    private String inflationRate;
}
