package rs.edu.raf.servis;


import rs.edu.raf.dto.KorisnikDTO;

public interface MailServis {
    public boolean posaljiMailZaRegistraciju(KorisnikDTO korisnik, String kod);
    public boolean posaljiMailZaPromenuLozinke(KorisnikDTO korisnik,String kod);
}
