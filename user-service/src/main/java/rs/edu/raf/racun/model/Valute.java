package rs.edu.raf.racun.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Valute {

    @Id
    @Column(unique = true)
    @NotBlank
    private String naziv;

    @NotBlank
    private String oznaka;

    @NotBlank
    private String simbol;

    @NotBlank
    private String zemlje; //nazivi zemalja e.g. Srbija,Francuska,Nemacka
}
