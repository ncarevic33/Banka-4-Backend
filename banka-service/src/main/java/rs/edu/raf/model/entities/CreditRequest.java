package rs.edu.raf.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private BigDecimal amount;
    private BigDecimal salary;
    private Boolean permanentEmployee;
    private Long currentEmploymentPeriod;
    private Long loanTerm;
    private String branchOffice;
    private Long bankAccountNumber;
    private String loanPurpose;
    private String status;
    private Long userId;

}
