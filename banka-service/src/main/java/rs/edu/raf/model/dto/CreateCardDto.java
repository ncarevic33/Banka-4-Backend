package rs.edu.raf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardDto {

    private String type;
    private String name;
    private String bankAccountNumber;
    private BigDecimal limit;

}
