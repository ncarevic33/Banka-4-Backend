package rs.edu.raf.currency.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.currency.dto.InflationDTO;
import rs.edu.raf.currency.model.Inflation;

@Component
public class InflationMapper {
    public InflationDTO inflationToInflationDTO(Inflation i) {
        InflationDTO dto = new InflationDTO();
        dto.setCountry(i.getCountry());
        dto.setYear(i.getInflYear());
        dto.setInflationRate(i.getInflationRate());
        return dto;
    }
}
