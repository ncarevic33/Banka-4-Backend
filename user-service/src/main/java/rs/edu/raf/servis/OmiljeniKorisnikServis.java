package rs.edu.raf.servis;

import rs.edu.raf.dto.OmiljeniKorisnikDTO;

import java.util.List;

public interface OmiljeniKorisnikServis {

    OmiljeniKorisnikDTO add(OmiljeniKorisnikDTO omiljeniKorisnikDTO);
    void edit(OmiljeniKorisnikDTO omiljeniKorisnikDTO);
    void delete(Long id);
    List<OmiljeniKorisnikDTO> findByIdKorisnika(Long id);

}
