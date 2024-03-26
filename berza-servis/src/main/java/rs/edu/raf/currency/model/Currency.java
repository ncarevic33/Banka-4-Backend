package rs.edu.raf.currency.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Currency {

    @Id
    @Column(unique = true)
    @NotBlank
    private String currencyCode;

    @NotBlank
    private String currencyName;

    @NotBlank
    private String currencySymbol;

    @NotBlank
    private String polity;
}
