package rs.edu.raf.korisnik.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import rs.edu.raf.korisnik.dto.KorisnikDTO;
import rs.edu.raf.korisnik.dto.NoviKorisnikDTO;
import rs.edu.raf.korisnik.dto.RegistrujKorisnikDTO;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.repository.KorisnikRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class KorisnikMapper{

    private KorisnikRepository korisnikRepository;


    public Korisnik noviKorisnikDtoToKorisnik(NoviKorisnikDTO noviKorisnikDTO) {
        Korisnik korisnik = new Korisnik();

        korisnik.setIme(noviKorisnikDTO.getIme());
        korisnik.setPrezime(noviKorisnikDTO.getPrezime());
        korisnik.setJmbg(noviKorisnikDTO.getJmbg());
        korisnik.setDatumRodjenja(noviKorisnikDTO.getDatumRodjenja());
        korisnik.setPol(noviKorisnikDTO.getPol());
        korisnik.setEmail(noviKorisnikDTO.getEmail());
        korisnik.setBrojTelefona(noviKorisnikDTO.getBrojTelefona());
        korisnik.setAdresa(noviKorisnikDTO.getAdresa());
        korisnik.setAktivan(noviKorisnikDTO.isAktivan());

        return korisnik;
    }

    public Korisnik registrujKorisnikDtoToKorisnik(RegistrujKorisnikDTO registrujKorisnikDTO) {

        Optional<Korisnik> korisnik = korisnikRepository.findByEmailAndAktivanIsTrue(registrujKorisnikDTO.getEmail());

        if (korisnik.isPresent()){
            korisnik.get().setPassword(registrujKorisnikDTO.getPassword());
            return korisnik.get();
        }

        return null;
    }

    public KorisnikDTO korisnikToKorisnikDto(Korisnik korisnik) {
        KorisnikDTO korisnikDTO = new KorisnikDTO();

        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setJmbg(korisnik.getJmbg());
        korisnikDTO.setDatumRodjenja(korisnik.getDatumRodjenja());
        korisnikDTO.setPol(korisnik.getPol());
        korisnikDTO.setEmail(korisnik.getEmail());
        korisnikDTO.setBrojTelefona(korisnik.getBrojTelefona());
        korisnikDTO.setAdresa(korisnik.getAdresa());
        korisnikDTO.setPovezaniRacuni(korisnik.getPovezaniRacuni());

        return korisnikDTO;
    }

}
