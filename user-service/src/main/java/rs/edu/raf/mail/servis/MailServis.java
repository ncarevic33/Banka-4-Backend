package rs.edu.raf.mail.servis;

import rs.edu.raf.korisnik.dto.IzmenaKorisnikaDTO;
import rs.edu.raf.korisnik.dto.NoviKorisnikDTO;

public interface MailServis {
    public boolean posaljiMailZaRegistraciju(NoviKorisnikDTO korisnik, String kod);
    public boolean posaljiMailZaPromenuLozinke(IzmenaKorisnikaDTO korisnik, String kod);
}
