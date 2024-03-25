package rs.edu.raf.servis;

import rs.edu.raf.model.SifrePlacanja;

public interface SifrePlacanjaServis {

    void init();
    SifrePlacanja findById(Long id);
    SifrePlacanja findByOblikAndOsnov(Integer oblikIOsnov);

}
