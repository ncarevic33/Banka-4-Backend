package rs.edu.raf.korisnik.servis;

import rs.edu.raf.korisnik.dto.*;

import java.util.List;

public interface KorisnikServis {

    public KorisnikDTO kreirajNovogKorisnika(NoviKorisnikDTO noviKorisnikDTO);
    public KorisnikDTO registrujNovogKorisnika(RegistrujKorisnikDTO registrujKorisnikDTO);
    public KorisnikDTO promeniSifruKorisnikaUzKod(IzmenaSifreUzKodDto izmenaSifreUzKodDto);
    public KorisnikDTO promeniSifruKorisnika(String email, IzmenaSifreDto izmenaSifreDto);
    public RadnikDTO kreirajNovogRadnika(NoviRadnikDTO noviRadnikDTO);
    public KorisnikDTO izmeniKorisnika(IzmenaKorisnikaDTO izmenaKorisnikaDTO);
    public RadnikDTO izmeniRadnika(IzmenaRadnikaDTO izmenaRadnikaDTO);
    public List<RadnikDTO> izlistajSveAktivneRadnike();
    public List<KorisnikDTO> izlistajSveAktivneKorisnike();
    public RadnikDTO nadjiAktivnogRadnikaPoEmail(String email);
    public KorisnikDTO nadjiAktivnogKorisnikaPoEmail(String email);
    public KorisnikDTO nadjiAktivnogKorisnikaPoJMBG(Long jmbg);
    public KorisnikDTO nadjiAktivnogKorisnikaPoBrojuTelefona(String brojTelefona);
}
