package rs.edu.raf.korisnik.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class IzmenaRadnikaDTO {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "Samo jedno ili više slova su dozvoljena!")
    private String prezime;

    @Pattern(regexp = "^[M|F]$", message = "Pol može biti M ili F!")
    private String pol;

    @Pattern(regexp = "^(\\+381|0)6[0-6]\\d{6,7}$\n", message = "Broj telefona mora biti formata +381/0 6 0-6 praćen sa 6 ili 7 cifara!")
    private String brojTelefona;

    private String adresa;

    private String password;

    @NotNull
    private String pozicija;

    @NotNull
    private String departman;

    @PositiveOrZero
    private Long permisije;

    private boolean aktivan;
}
