package rs.edu.raf.racun.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class NoviTekuciRacunDTO {

    private Long vlasnik; //korisnik id

    private Long zaposleni; //radnik id

    private String vrstaRacuna; ////Poslovni, Lični, Štedni, Penzionerski, Devizni, Studentski
}
