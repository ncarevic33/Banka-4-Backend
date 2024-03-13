package rs.edu.raf.racun.servis.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.repository.KorisnikRepository;
import rs.edu.raf.racun.dto.*;
import rs.edu.raf.racun.mapper.FirmaMapper;
import rs.edu.raf.racun.mapper.RacunMapper;
import rs.edu.raf.racun.model.DevizniRacun;
import rs.edu.raf.racun.model.Firma;
import rs.edu.raf.racun.model.PravniRacun;
import rs.edu.raf.racun.model.TekuciRacun;
import rs.edu.raf.racun.repository.FirmaRepository;
import rs.edu.raf.racun.servis.RacunServis;
import rs.edu.raf.transakcija.repository.DevizniRacunRepository;
import rs.edu.raf.transakcija.repository.PravniRacunRepository;
import rs.edu.raf.transakcija.repository.TekuciRacunRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RacunServisImpl implements RacunServis {

    private DevizniRacunRepository devizniRacunRepository;
    private PravniRacunRepository pravniRacunRepository;
    private TekuciRacunRepository tekuciRacunRepository;
    private KorisnikRepository korisnikRepository;
    private FirmaRepository firmaRepository;

    private RacunMapper racunMapper;
    private FirmaMapper firmaMapper;

    @Autowired
    public RacunServisImpl(DevizniRacunRepository devizniRacunRepository, PravniRacunRepository pravniRacunRepository, TekuciRacunRepository tekuciRacunRepository, KorisnikRepository korisnikRepository, FirmaRepository firmaRepository, RacunMapper racunMapper, FirmaMapper firmaMapper) {
        this.devizniRacunRepository = devizniRacunRepository;
        this.pravniRacunRepository = pravniRacunRepository;
        this.tekuciRacunRepository = tekuciRacunRepository;
        this.korisnikRepository = korisnikRepository;
        this.firmaRepository = firmaRepository;
        this.racunMapper = racunMapper;
        this.firmaMapper = firmaMapper;
    }

    @Override
    public DevizniRacun kreirajDevizniRacun(NoviDevizniRacunDTO noviDevizniRacunDTO) {
        DevizniRacun dr = racunMapper.noviDevizniRacunDTOToDevizniRacun(noviDevizniRacunDTO);
        DevizniRacun drRepo = this.devizniRacunRepository.save(dr);
        Optional<Korisnik> korisnik = korisnikRepository.findById(drRepo.getVlasnik());
        korisnik.ifPresent(k -> dodajDevizniRacunKorisniku(drRepo, k));
        return drRepo;
    }

    @Override
    public PravniRacun kreirajPravniRacun(NoviPravniRacunDTO noviPravniRacunDTO) {
        PravniRacun pr = racunMapper.noviPravniRacunDTOToPravniRacun(noviPravniRacunDTO);
        PravniRacun prRepo = this.pravniRacunRepository.save(pr);
        Optional<Firma> firma = firmaRepository.findById(prRepo.getFirma());
        firma.ifPresent(f -> dodajPravniRacunFirmi(prRepo, f));
        return prRepo;
    }

    @Override
    public TekuciRacun kreirajTekuciRacun(NoviTekuciRacunDTO noviTekuciRacunDTO) {
        TekuciRacun tr = racunMapper.noviTekuciRacunDTOToTekuciRacun(noviTekuciRacunDTO);
        TekuciRacun trRepo = this.tekuciRacunRepository.save(tr);
        Optional<Korisnik> korisnik = korisnikRepository.findById(trRepo.getVlasnik());
        korisnik.ifPresent(k -> dodajTekuciRacunKorisniku(trRepo, k));
        return trRepo;
    }

    @Override
    public List<RacunDTO> izlistavanjeRacunaJednogKorisnika(Long idKorisnika) {
        Korisnik k = korisnikRepository.findById(idKorisnika).orElse(null);
        List<RacunDTO> racunDTOs = new ArrayList<>();
        if (k != null) {
            RacunDTO dto;
            List<String> racuni = List.of(k.getPovezaniRacuni().split(","));
            for (String r : racuni) {
                String vrsta = nadjiVrstuRacuna(Long.parseLong(r));
                if (Objects.equals(vrsta, "DevizniRacun")) {
                    DevizniRacun dr = this.devizniRacunRepository.findByBrojRacunaAndAktivanIsTrue(Long.parseLong(r)).orElse(null);
                    if (dr != null) {
                        dto = racunMapper.devizniRacunToRacunDTO(dr);
                        racunDTOs.add(dto);
                    }
                } else if (Objects.equals(vrsta, "TekuciRacun")) {
                    TekuciRacun tr = this.tekuciRacunRepository.findByBrojRacunaAndAktivanIsTrue(Long.parseLong(r)).orElse(null);
                    if (tr != null) {
                        dto = racunMapper.tekuciRacunToRacunDTO(tr);
                        racunDTOs.add(dto);
                    }
                }
            }
        } else {
            Firma f = firmaRepository.findById(idKorisnika).orElse(null);
            if (f != null) {
                RacunDTO dto;
                List<String> racuni = List.of(f.getPovezaniRacuni().split(","));
                for (String r : racuni) {
                    PravniRacun pr = this.pravniRacunRepository.findByBrojRacunaAndAktivanIsTrue(Long.parseLong(r)).orElse(null);
                    if (pr != null) {
                        dto = racunMapper.pravniRacunToRacunDTO(pr);
                        racunDTOs.add(dto);
                    }
                }
            }
        }
        return racunDTOs;
    }

    @Override
    public RacunDTO nadjiAktivanRacunPoID(Long id) {
        DevizniRacun dr = this.devizniRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
        PravniRacun pr = this.pravniRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
        TekuciRacun tr = this.tekuciRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
        RacunDTO dto;
        if (dr != null) {
            dto = racunMapper.devizniRacunToRacunDTO(dr);
            return dto;
        } else if (pr != null) {
            dto = racunMapper.pravniRacunToRacunDTO(pr);
            return dto;
        } else if (tr != null) {
            dto = racunMapper.tekuciRacunToRacunDTO(tr);
            return dto;
        }
        return null;
    }

    @Override
    public DevizniRacun nadjiAktivanDevizniRacunPoID(Long id) {
        return this.devizniRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
    }

    @Override
    public PravniRacun nadjiAktivanPravniRacunPoID(Long id) {
        return this.pravniRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
    }

    @Override
    public TekuciRacun nadjiAktivanTekuciRacunPoID(Long id) {
        return this.tekuciRacunRepository.findByIdAndAktivanIsTrue(id).orElse(null);
    }

    @Override
    public RacunDTO nadjiAktivanRacunPoBrojuRacuna(Long BrojRacuna) {
        RacunDTO dto;
        if (BrojRacuna % 100 == 11) {
            DevizniRacun dr = this.devizniRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
            if (dr != null) {
                dto = racunMapper.devizniRacunToRacunDTO(dr);
                return dto;
            }
        } else if (BrojRacuna % 100 == 22) {
            PravniRacun pr = this.pravniRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
            if (pr != null) {
                dto = racunMapper.pravniRacunToRacunDTO(pr);
                return dto;
            }
        } else if (BrojRacuna % 100 == 33) {
            TekuciRacun tr = this.tekuciRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
            if (tr != null) {
                dto = racunMapper.tekuciRacunToRacunDTO(tr);
                return dto;
            }
        }
        return null;
    }

    @Override
    public DevizniRacun nadjiAktivanDevizniRacunPoBrojuRacuna(Long BrojRacuna) {
        return this.devizniRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
    }

    @Override
    public PravniRacun nadjiAktivanPravniRacunPoBrojuRacuna(Long BrojRacuna) {
        return this.pravniRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
    }

    @Override
    public TekuciRacun nadjiAktivanTekuciRacunPoBrojuRacuna(Long BrojRacuna) {
        return this.tekuciRacunRepository.findByBrojRacunaAndAktivanIsTrue(BrojRacuna).orElse(null);
    }

    @Override
    public boolean dodajDevizniRacunKorisniku(DevizniRacun devizniRacun, Korisnik korisnik) {
        devizniRacun.setVlasnik(korisnik.getId());
        if (korisnik.getPovezaniRacuni() == null) {
            korisnik.setPovezaniRacuni(devizniRacun.getBrojRacuna().toString());
        } else {
            List<String> racuni = List.of(korisnik.getPovezaniRacuni().split(","));
            for (String r : racuni) {
                if (r.equals(devizniRacun.getBrojRacuna().toString())) {
                    return false;
                }
            }
        }
        korisnik.setPovezaniRacuni(korisnik.getPovezaniRacuni().concat("," + devizniRacun.getBrojRacuna()));
        this.devizniRacunRepository.save(devizniRacun);
        this.korisnikRepository.save(korisnik);
        return true;
    }

    @Override
    public boolean dodajPravniRacunFirmi(PravniRacun pravniRacun, Firma firma) {
        pravniRacun.setFirma(firma.getId());
        if (firma.getPovezaniRacuni() == null) {
            firma.setPovezaniRacuni(pravniRacun.getBrojRacuna().toString());
        } else {
            List<String> racuni = List.of(firma.getPovezaniRacuni().split(","));
            for (String r : racuni) {
                if (r.equals(pravniRacun.getBrojRacuna().toString())) {
                    return false;
                }
            }
            firma.setPovezaniRacuni(firma.getPovezaniRacuni().concat("," + pravniRacun.getBrojRacuna()));
        }
        this.pravniRacunRepository.save(pravniRacun);
        this.firmaRepository.save(firma);
        return true;
    }

    @Override
    public boolean dodajTekuciRacunKorisniku(TekuciRacun tekuciRacun, Korisnik korisnik) {
        tekuciRacun.setVlasnik(korisnik.getId());
        if (korisnik.getPovezaniRacuni() == null) {
            korisnik.setPovezaniRacuni(tekuciRacun.getBrojRacuna().toString());
        } else {
            List<String> racuni = List.of(korisnik.getPovezaniRacuni().split(","));
            for (String r : racuni) {
                if (r.equals(tekuciRacun.getBrojRacuna().toString())) {
                    return false;
                }
            }
            korisnik.setPovezaniRacuni(korisnik.getPovezaniRacuni().concat("," + tekuciRacun.getBrojRacuna()));
        }
        this.tekuciRacunRepository.save(tekuciRacun);
        this.korisnikRepository.save(korisnik);
        return true;
    }

    @Override
    public Long generisiBrojRacuna(String tipRacuna) {
        if (tipRacuna.equals("DevizniRacun")) {
            Long id = devizniRacunRepository.findTop1ByOrderByIdDesc() * 100L;
            return 444000000000000011L + id; //444 sifra banke 0000 filial
        } else if (tipRacuna.equals("PravniRacun")) {
            Long id = pravniRacunRepository.findTop1ByOrderByIdDesc() * 100L;
            return 444000000000000022L + id; //444 sifra banke 0000 filial
        } else if (tipRacuna.equals("TekuciRacun")) {
            Long id = tekuciRacunRepository.findTop1ByOrderByIdDesc() * 100L;
            return 444000000000000033L + id; //444 sifra banke 0000 filial
        }
        return null;
    }

    @Override
    public String nadjiVrstuRacuna(Long BrojRacuna) {
        if (BrojRacuna % 100 == 11) {
            return "DevizniRacun";
        } else if (BrojRacuna % 100 == 22) {
            return "PravniRacun";
        } else if (BrojRacuna % 100 == 33) {
            return "TekuciRacun";
        }
        return null;
    }

    @Override
    public List<FirmaDTO> izlistajSveFirme() {
        List<FirmaDTO> dtos = new ArrayList<>();
        List<Firma> firme = firmaRepository.findAll();
        for (Firma f : firme) {
            FirmaDTO dto = firmaMapper.firmaToFirmaDTO(f);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Firma kreirajFirmu(NovaFirmaDTO firmaDTO) {
        Firma f = firmaMapper.novaFirmaDTOToFirma(firmaDTO);
        return this.firmaRepository.save(f);
    }

}
