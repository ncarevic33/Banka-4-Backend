package rs.edu.raf.korisnik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IzmenaSifreUzKodDto {
    private String email;
    private String sifra;
    private String kod;
}
