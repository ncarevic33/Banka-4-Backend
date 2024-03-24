package rs.edu.raf.model.entities.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SablonTransakcije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
