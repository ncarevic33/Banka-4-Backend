package rs.edu.raf.mail.servis;

import rs.edu.raf.korisnik.model.Korisnik;

public interface MailServis {
    public boolean posaljiMailZaRegistraciju(Korisnik korisnik,String kod);
    public boolean posaljiMailZaPromenuLozinke(Korisnik korisnik,String kod);
}
