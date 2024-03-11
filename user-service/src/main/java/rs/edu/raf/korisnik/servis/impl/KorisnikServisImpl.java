package rs.edu.raf.korisnik.servis.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.dto.*;
import rs.edu.raf.korisnik.mapper.KorisnikMapper;
import rs.edu.raf.korisnik.mapper.RadnikMapper;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.model.Radnik;
import rs.edu.raf.korisnik.repository.KorisnikRepository;
import rs.edu.raf.korisnik.repository.RadnikRepository;
import rs.edu.raf.korisnik.servis.KorisnikServis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KorisnikServisImpl implements KorisnikServis {

    private KorisnikRepository korisnikRepository;

    private RadnikRepository radnikRepository;

    private KorisnikMapper korisnikMapper;

    private RadnikMapper radnikMapper;

    @Override
    public Korisnik kreirajNovogKorisnika(NoviKorisnikDTO noviKorisnikDTO) {

        Korisnik korisnik = korisnikMapper.noviKorisnikDtoToKorisnik(noviKorisnikDTO);

        return korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik registrujNovogKorisnika(RegistrujKorisnikDTO registrujKorisnikDTO) {

        Korisnik korisnik = korisnikMapper.registrujKorisnikDtoToKorisnik(registrujKorisnikDTO);

        return korisnikRepository.save(korisnik);
    }

    @Override
    public Radnik kreirajNovogRadnika(NoviRadnikDTO noviRadnikDTO) {
        
        Radnik radnik = radnikMapper.noviRadnikDtoToRadnik(noviRadnikDTO);

        return radnikRepository.save(radnik);
    }

    @Override
    public Korisnik izmeniKorisnika(IzmenaKorisnikaDTO izmenaKorisnikaDTO) {

        Optional<Korisnik> korisnik = korisnikRepository.findById(izmenaKorisnikaDTO.getId());

        if (korisnik.isPresent()) {
            korisnik.get().setPrezime(izmenaKorisnikaDTO.getPrezime());
            korisnik.get().setPol(izmenaKorisnikaDTO.getPol());
            korisnik.get().setEmail(izmenaKorisnikaDTO.getEmail());
            korisnik.get().setBrojTelefona(izmenaKorisnikaDTO.getBrojTelefona());
            korisnik.get().setAdresa(izmenaKorisnikaDTO.getAdresa());
            korisnik.get().setPassword(izmenaKorisnikaDTO.getPassword());
            korisnik.get().setPovezaniRacuni(izmenaKorisnikaDTO.getPovezaniRacuni());
            korisnik.get().setAktivan(izmenaKorisnikaDTO.isAktivan());

            return korisnikRepository.save(korisnik.get());
        }
        return null;
    }

    @Override
    public Radnik izmeniRadnika(IzmenaRadnikaDTO izmenaRadnikaDTO) {

        Optional<Radnik> radnik = radnikRepository.findById(izmenaRadnikaDTO.getId());

        if (radnik.isPresent()) {
            radnik.get().setPrezime(izmenaRadnikaDTO.getPrezime());
            radnik.get().setPol(izmenaRadnikaDTO.getPol());
            radnik.get().setBrojTelefona(izmenaRadnikaDTO.getBrojTelefona());
            radnik.get().setAdresa(izmenaRadnikaDTO.getAdresa());
            radnik.get().setPassword(izmenaRadnikaDTO.getPassword());
            radnik.get().setPozicija(izmenaRadnikaDTO.getPozicija());
            radnik.get().setDepartman(izmenaRadnikaDTO.getDepartman());
            radnik.get().setPermisije(izmenaRadnikaDTO.getPermisije());
            radnik.get().setAktivan(izmenaRadnikaDTO.isAktivan());

            return radnikRepository.save(radnik.get());
        }

        return null;
    }

    @Override
    public List<RadnikDTO> izlistajSveAktivneRadnike() {
        
        List<Radnik> radnici = radnikRepository.findAllByAktivanIsTrue();
        
        return radnici.stream().map(radnikMapper::radnikToRadnikDto).collect(Collectors.toList());
    }

    @Override
    public List<KorisnikDTO> izlistajSveAktivneKorisnike() {
        
        List<Korisnik> korisnici = korisnikRepository.findAllByAktivanIsTrue();
        
        return korisnici.stream().map(korisnikMapper::korisnikToKorisnikDto).collect(Collectors.toList());
    }

    @Override
    public RadnikDTO nadjiAktivnogRadnikaPoEmail(String email) {

        Optional<Radnik> radnik = radnikRepository.findByEmailAndAktivanIsTrue(email);

        return radnik.map(radnikMapper::radnikToRadnikDto).orElse(null);
    }

    @Override
    public KorisnikDTO nadjiAktivnogKorisnikaPoEmail(String email) {

        Optional<Korisnik> korisnik = korisnikRepository.findByEmailAndAktivanIsTrue(email);

        return korisnik.map(korisnikMapper::korisnikToKorisnikDto).orElse(null);
    }

    @Override
    public KorisnikDTO nadjiAktivnogKorisnikaPoJMBG(Long jmbg) {

        Optional<Korisnik> korisnik = korisnikRepository.findByJmbgAndAktivanIsTrue(jmbg);

        return korisnik.map(korisnikMapper::korisnikToKorisnikDto).orElse(null);
    }

    @Override
    public KorisnikDTO nadjiAktivnogKorisnikaPoBrojuTelefona(String brojTelefona) {

        Optional<Korisnik> korisnik = korisnikRepository.findByBrojTelefonaAndAktivanIsTrue(brojTelefona);

        return korisnik.map(korisnikMapper::korisnikToKorisnikDto).orElse(null);
    }

    @Override
    public UserDTO ulogujUsera(String username, String password) {
        return null;
    }
}
