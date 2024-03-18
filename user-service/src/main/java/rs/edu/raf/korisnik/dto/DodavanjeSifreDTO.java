package rs.edu.raf.korisnik.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DodavanjeSifreDTO {

    //[a-zA-Z0-9._%+-] je zapravo (a..z+A..Z+0..9+.+_+%+++-)^*
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email adresa mora biti validna!")
    private String email;
}
