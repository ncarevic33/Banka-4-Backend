package rs.edu.raf.transakcija.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "prenos_sredstava")
@AllArgsConstructor
@NoArgsConstructor
public class PrenosSredstava {

    @Id
    private String id;

    private Long racunPosiljaoca;

    private Long racunPrimaoca;

    private BigDecimal iznos;

    private Long vreme;

    private String status;

    private Long vremeIzvrsavanja = null;

}