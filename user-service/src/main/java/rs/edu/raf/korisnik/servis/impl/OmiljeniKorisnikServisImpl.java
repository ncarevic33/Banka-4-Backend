package rs.edu.raf.korisnik.servis.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.dto.OmiljeniKorisnikDTO;
import rs.edu.raf.korisnik.repository.OmiljeniKorisnikRepository;
import rs.edu.raf.korisnik.servis.OmiljeniKorisnikMapper;
import rs.edu.raf.korisnik.servis.OmiljeniKorisnikServis;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class OmiljeniKorisnikServisImpl implements OmiljeniKorisnikServis {

    @Autowired
    private OmiljeniKorisnikRepository omiljeniKorisnikRepository;
    @Override
    public OmiljeniKorisnikDTO add(OmiljeniKorisnikDTO omiljeniKorisnikDTO) {
        return OmiljeniKorisnikMapper.toDTO(omiljeniKorisnikRepository.save(OmiljeniKorisnikMapper.toEntity(omiljeniKorisnikDTO)));
    }

    @Override
    public void edit(OmiljeniKorisnikDTO omiljeniKorisnikDTO) {
        omiljeniKorisnikRepository.save(OmiljeniKorisnikMapper.toEntity(omiljeniKorisnikDTO));
    }

    @Override
    public void delete(Long id) {
        omiljeniKorisnikRepository.deleteById(id);
    }

    @Override
    public OmiljeniKorisnikDTO findById(Long id) {
        return OmiljeniKorisnikMapper.toDTO(omiljeniKorisnikRepository.findById(id).orElse(null));
    }
}
