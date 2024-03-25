package rs.edu.raf.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private String number;

    private String type;
    private String name;
    private Long creationDate;
    private Long expirationDate;
    private String bankAccountNumber;
    private String cvv;
    private BigDecimal cardLimit;
    private String status;

    private Boolean blocked;


}
