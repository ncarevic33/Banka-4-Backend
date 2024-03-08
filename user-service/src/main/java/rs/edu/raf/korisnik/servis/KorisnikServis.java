package rs.edu.raf.korisnik.servis;

import rs.edu.raf.korisnik.dto.*;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.model.Radnik;

import java.util.List;

public interface KorisnikServis {
    public Korisnik kreirajNovogKorisnika(NoviKorisnikDTO noviKorisnikDTO);
    public Radnik kreirajNovogRadnika(NoviRadnikDTO noviRadnikDTO);
    public List<RadnikDTO> izlistajSveAktivneRadnike();
    public List<KorisnikDTO> izlistajSveAktivneKorisnike();
    public RadnikDTO nadjiAktivnogRadnikaPoEmail(String email);
    public KorisnikDTO nadjiAktivnogKorisnikaPoEmail(String email);
    public KorisnikDTO nadjiAktivnogKorisnikaPoJMBG(Long jmbg);
    public KorisnikDTO nadjiAktivnogKorisnikaPoBrojuTelefona(String brojTelefona);
    public UserDTO ulogujUsera(String username,String password);

}
