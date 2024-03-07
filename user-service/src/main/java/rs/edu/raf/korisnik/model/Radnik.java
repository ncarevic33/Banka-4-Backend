package rs.edu.raf.korisnik.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
public class Radnik {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String Ime;

    @Getter
    @Setter
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
    @Pattern(regexp = "^(\\+381|0)6[0-6]\\d{6,7}$", message = "Broj telefona mora biti formata +381/0 6 0-6 praćen sa 6 ili 7 cifara!")
    private String BrojTelefona;

    @Setter
    @Getter
    private String Adresa;

    @Getter
    @NotBlank
    private String Username;

    @Setter
    @Getter
    private String Password;

    @Getter
    private String SaltPassword;

    @Setter
    @Getter
    @NotNull
    private String Pozicija;

    @Setter
    @Getter
    @NotNull
    private String Departman;

    @Setter
    @Getter
    @Positive
    private Long Permisije;

    @Setter
    @Getter
    private boolean Aktivan;
}
