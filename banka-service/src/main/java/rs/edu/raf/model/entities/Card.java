package rs.edu.raf.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String type;
    private String name;
    private Long creationDate;
    private Long expirationDate;
    private Long bankAccountNumber;
    private String cvv;
    private BigDecimal cardLimit;
    private String status;

    private Boolean blocked;

}
