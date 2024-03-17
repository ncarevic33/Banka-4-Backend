package rs.edu.raf.currency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(InflationId.class)
public class Inflation {

    @Id
    @NotBlank
    private String currency; //CurrencyCode

    @Id
    @NotBlank
    private String year;

    @Id
    @NotBlank
    private String inflationRate;
}
