package rs.edu.raf.korisnik.servis.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.dto.*;
import rs.edu.raf.korisnik.exceptions.JMBGDateMismatchException;
import rs.edu.raf.korisnik.mapper.KorisnikMapper;
import rs.edu.raf.korisnik.mapper.RadnikMapper;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.model.Radnik;
import rs.edu.raf.korisnik.repository.KorisnikRepository;
import rs.edu.raf.korisnik.repository.RadnikRepository;
import rs.edu.raf.korisnik.servis.KodServis;
import rs.edu.raf.korisnik.servis.KorisnikServis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    private KodServis kodServis;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public KorisnikDTO kreirajNovogKorisnika(NoviKorisnikDTO noviKorisnikDTO) {

        Korisnik korisnik = korisnikMapper.noviKorisnikDtoToKorisnik(noviKorisnikDTO);

        LocalDateTime datumRodjenja = LocalDateTime.ofInstant(Instant.ofEpochMilli(korisnik.getDatumRodjenja()), ZoneOffset.systemDefault());
        if (korisnik.getJmbg().toString().length() == 12) {
            if (datumRodjenja.getDayOfMonth() != Integer.parseInt(korisnik.getJmbg().toString().substring(0, 1)) ||
                    datumRodjenja.getMonthValue() != Integer.parseInt(korisnik.getJmbg().toString().substring(1, 3)) ||
                    datumRodjenja.getYear() % 1000 != Integer.parseInt(korisnik.getJmbg().toString().substring(3, 6))) {
                throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
            }
        }
        else{
            if (datumRodjenja.getDayOfMonth() != Integer.parseInt(korisnik.getJmbg().toString().substring(0, 2)) ||
                    datumRodjenja.getMonthValue() != Integer.parseInt(korisnik.getJmbg().toString().substring(2, 4)) ||
                    datumRodjenja.getYear() % 1000 != Integer.parseInt(korisnik.getJmbg().toString().substring(4, 7))) {
                throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
            }
        }

        return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik));
    }

    @Override
    public KorisnikDTO registrujNovogKorisnika(RegistrujKorisnikDTO registrujKorisnikDTO) {

        Optional<Korisnik> korisnik = korisnikRepository.findByEmailAndAktivanIsTrue(registrujKorisnikDTO.getEmail());

        if (korisnik.isPresent()){
            korisnik.get().setPassword(registrujKorisnikDTO.getPassword());
            return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik.get()));
        }

        return null;
    }

    @Override
    public KorisnikDTO promeniSifruKorisnikaUzKod(IzmenaSifreUzKodDto izmenaSifreUzKodDto) {
        if(kodServis.dobarKod(izmenaSifreUzKodDto.getEmail(), izmenaSifreUzKodDto.getKod(),true)) {
            Korisnik korisnik = korisnikMapper.izmenaSifreDtoToKorisnik(izmenaSifreUzKodDto);
            return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik));
        }
        return null;
    }

    @Override
    public KorisnikDTO promeniSifruKorisnika(String email, IzmenaSifreDto izmenaSifreDto) {
        Optional<Korisnik> korisnik = korisnikRepository.findByEmailAndAktivanIsTrue(email);
        if(korisnik.isPresent()) {
            if(bCryptPasswordEncoder.matches(izmenaSifreDto.getStaraSifra(),korisnik.get().getPassword())) {
                korisnik.get().setPassword(bCryptPasswordEncoder.encode(izmenaSifreDto.getNovaSifra()));
                return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik.get()));
            }
        }
        return null;
    }

    @Override
    public RadnikDTO kreirajNovogRadnika(NoviRadnikDTO noviRadnikDTO) {

        Radnik radnik = radnikMapper.noviRadnikDtoToRadnik(noviRadnikDTO);

        LocalDateTime datumRodjenja = LocalDateTime.ofInstant(Instant.ofEpochMilli(radnik.getDatumRodjenja()), ZoneOffset.systemDefault());
        if (radnik.getJmbg().toString().length() == 12) {
            if (datumRodjenja.getDayOfMonth() != Integer.parseInt(radnik.getJmbg().toString().substring(0, 1)) ||
                datumRodjenja.getMonthValue() != Integer.parseInt(radnik.getJmbg().toString().substring(1, 3)) ||
                datumRodjenja.getYear() % 1000 != Integer.parseInt(radnik.getJmbg().toString().substring(3, 6))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
            }
        }
        else{
            if (datumRodjenja.getDayOfMonth() != Integer.parseInt(radnik.getJmbg().toString().substring(0, 2)) ||
                datumRodjenja.getMonthValue() != Integer.parseInt(radnik.getJmbg().toString().substring(2, 4)) ||
                datumRodjenja.getYear() % 1000 != Integer.parseInt(radnik.getJmbg().toString().substring(4, 7))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
            }
        }

        return radnikMapper.radnikToRadnikDto(radnikRepository.save(radnik));
    }

    @Override
    public KorisnikDTO izmeniKorisnika(IzmenaKorisnikaDTO izmenaKorisnikaDTO) {

        Optional<Korisnik> korisnik = korisnikRepository.findById(izmenaKorisnikaDTO.getId());

        if (korisnik.isPresent()) {
            LocalDateTime datumRodjenja = LocalDateTime.ofInstant(Instant.ofEpochMilli(korisnik.get().getDatumRodjenja()), ZoneOffset.systemDefault());
            if (korisnik.get().getJmbg().toString().length() == 12) {
                if (datumRodjenja.getDayOfMonth() != Integer.parseInt(korisnik.get().getJmbg().toString().substring(0, 1)) ||
                        datumRodjenja.getMonthValue() != Integer.parseInt(korisnik.get().getJmbg().toString().substring(1, 3)) ||
                        datumRodjenja.getYear() % 1000 != Integer.parseInt(korisnik.get().getJmbg().toString().substring(3, 6))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
                }
            }
            else{
                if (datumRodjenja.getDayOfMonth() != Integer.parseInt(korisnik.get().getJmbg().toString().substring(0, 2)) ||
                        datumRodjenja.getMonthValue() != Integer.parseInt(korisnik.get().getJmbg().toString().substring(2, 4)) ||
                        datumRodjenja.getYear() % 1000 != Integer.parseInt(korisnik.get().getJmbg().toString().substring(4, 7))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
                }
            }

            korisnik.get().setPrezime(izmenaKorisnikaDTO.getPrezime());
            korisnik.get().setPol(izmenaKorisnikaDTO.getPol());
            korisnik.get().setEmail(izmenaKorisnikaDTO.getEmail());
            korisnik.get().setBrojTelefona(izmenaKorisnikaDTO.getBrojTelefona());
            korisnik.get().setAdresa(izmenaKorisnikaDTO.getAdresa());
            korisnik.get().setPassword(izmenaKorisnikaDTO.getPassword());
            korisnik.get().setPovezaniRacuni(izmenaKorisnikaDTO.getPovezaniRacuni());
            korisnik.get().setAktivan(izmenaKorisnikaDTO.isAktivan());

            return korisnikMapper.korisnikToKorisnikDto(korisnikRepository.save(korisnik.get()));
        }
        return null;
    }

    @Override
    public RadnikDTO izmeniRadnika(IzmenaRadnikaDTO izmenaRadnikaDTO) {

        Optional<Radnik> radnik = radnikRepository.findById(izmenaRadnikaDTO.getId());

        if (radnik.isPresent()) {
            LocalDateTime datumRodjenja = LocalDateTime.ofInstant(Instant.ofEpochMilli(radnik.get().getDatumRodjenja()), ZoneOffset.systemDefault());
            if (radnik.get().getJmbg().toString().length() == 12) {
                if (datumRodjenja.getDayOfMonth() != Integer.parseInt(radnik.get().getJmbg().toString().substring(0, 1)) ||
                        datumRodjenja.getMonthValue() != Integer.parseInt(radnik.get().getJmbg().toString().substring(1, 3)) ||
                        datumRodjenja.getYear() % 1000 != Integer.parseInt(radnik.get().getJmbg().toString().substring(3, 6))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
                }
            }
            else{
                if (datumRodjenja.getDayOfMonth() != Integer.parseInt(radnik.get().getJmbg().toString().substring(0, 2)) ||
                        datumRodjenja.getMonthValue() != Integer.parseInt(radnik.get().getJmbg().toString().substring(2, 4)) ||
                        datumRodjenja.getYear() % 1000 != Integer.parseInt(radnik.get().getJmbg().toString().substring(4, 7))) {
                    throw new JMBGDateMismatchException("Datum rodjenja i JMBG se ne poklapaju!");
                }
            }

            radnik.get().setPrezime(izmenaRadnikaDTO.getPrezime());
            radnik.get().setPol(izmenaRadnikaDTO.getPol());
            radnik.get().setBrojTelefona(izmenaRadnikaDTO.getBrojTelefona());
            radnik.get().setAdresa(izmenaRadnikaDTO.getAdresa());
            radnik.get().setPassword(izmenaRadnikaDTO.getPassword());
            radnik.get().setPozicija(izmenaRadnikaDTO.getPozicija());
            radnik.get().setDepartman(izmenaRadnikaDTO.getDepartman());
            radnik.get().setPermisije(izmenaRadnikaDTO.getPermisije());
            radnik.get().setAktivan(izmenaRadnikaDTO.isAktivan());

            return radnikMapper.radnikToRadnikDto(radnikRepository.save(radnik.get()));
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
}

