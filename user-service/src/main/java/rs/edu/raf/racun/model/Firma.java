package rs.edu.raf.racun.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Firma {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotBlank
    private String nazivPreduzeca;

    @NotBlank
    private String brojTelefona;

    @NotBlank
    private String brojFaksa;

    @NotNull
    private Integer PIB;

    @NotNull
    private Integer maticniBroj;

    @NotNull
    private Integer sifraDelatnosti;

    @NotNull
    private Integer registarskiBroj;
}
