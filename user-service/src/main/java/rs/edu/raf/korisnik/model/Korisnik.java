package rs.edu.raf.korisnik.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Date;

@Entity
public class Korisnik {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String Ime;

    @Setter
    @Getter
    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String Prezime;

    @Getter
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])(\\\\d{2})(\\\\d{3})$\n", message = "Nepravilan JMBG!")
    private Long JMBG;

    @Getter
    private Long DatumRodjenja;

    @Setter
    @Getter
    @Pattern(regexp = "^[M|F]$", message = "Pol može biti M ili F!")
    private String Pol;

    @Getter
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email adresa mora biti validna!")
    private String Email;

    @Setter
    @Getter
    @Pattern(regexp = "^(\\+381|0)6[0-6]\\d{6,7}$\n", message = "Broj telefona mora biti formata +381/0 6 0-6 praćen sa 6 ili 7 cifara!")
    private String BrojTelefona;

    @Setter
    @Getter
    @NotNull
    private String Adresa;

    @Getter
    private String Password;

    @Setter
    @Getter
    private String SaltPassword;

    @Setter
    @Getter
    private String PovezaniRacuni;

    @Setter
    @Getter
    private boolean Aktivan;
}
