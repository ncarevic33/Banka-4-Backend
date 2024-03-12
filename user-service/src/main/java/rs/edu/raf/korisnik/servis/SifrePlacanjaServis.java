package rs.edu.raf.korisnik.servis;

import rs.edu.raf.korisnik.model.SifrePlacanja;

public interface SifrePlacanjaServis {

    void init();
    SifrePlacanja findById(Long id);
    SifrePlacanja findByOblikAndOsnov(Integer oblikIOsnov);

}
