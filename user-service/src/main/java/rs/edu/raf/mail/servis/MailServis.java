package rs.edu.raf.mail.servis;


import rs.edu.raf.korisnik.dto.KorisnikDTO;

public interface MailServis {
    public boolean posaljiMailZaRegistraciju(KorisnikDTO korisnik, String kod);
    public boolean posaljiMailZaPromenuLozinke(KorisnikDTO korisnik,String kod);
}
