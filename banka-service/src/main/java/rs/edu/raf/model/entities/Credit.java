package rs.edu.raf.model.entities;

import jakarta.persistence.Entity;
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
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private BigDecimal amount;
    private Long bankAccountNumber;
    private Long creditRequestId;

    private Long loanTerm;
    private BigDecimal nominalInterestRate;
    private BigDecimal effectiveInterestRate;
    private Long contractDate;
    private Long loanMaturityDate;
    private BigDecimal installmentAmount;
    private String currency;
    private BigDecimal prepayment;
    private BigDecimal remainingDebt;
    private Long nextInstallmentDate;

}
