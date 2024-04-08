package rs.edu.raf.currency.servis;

import rs.edu.raf.currency.dto.InflationDTO;

import java.util.List;

public interface InflationService {

    List<InflationDTO> getInflationByCountry(String country);
    List<InflationDTO> getInflationByCountryAndYear(String country, String year);
}
