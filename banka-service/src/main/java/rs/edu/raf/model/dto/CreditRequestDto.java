package rs.edu.raf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditRequestDto {

    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal salary;
    private Long currentEmploymentPeriod;
    private Long loanTerm;
    private String branchOffice;
    private Long bankAccountNumber;
    private String loanPurpose;
    private Boolean permanentEmployee;

}
