package rs.edu.raf.korisnik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IzmenaSifreDto {
    private String staraSifra;
    private String novaSifra;
}
