package rs.edu.raf.korisnik.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class RegistrujKorisnikDTO {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email adresa mora biti validna!")
    private String email;

    @Pattern(regexp = "^(\\+381|0)6[0-6]\\d{6,7}$\n", message = "Broj telefona mora biti formata +381/0 6 0-6 praćen sa 6 ili 7 cifara!")
    private String brojTelefona;

    private String brojRacuna;

    private String password;

    private String code;
}
