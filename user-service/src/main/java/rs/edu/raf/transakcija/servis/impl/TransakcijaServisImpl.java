package rs.edu.raf.transakcija.servis.impl;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.RealizacijaTransakcije;
import rs.edu.raf.racun.model.DevizniRacun;
import rs.edu.raf.racun.model.PravniRacun;
import rs.edu.raf.racun.model.TekuciRacun;
import rs.edu.raf.racun.servis.RacunServis;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.mapper.DtoOriginalMapper;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.repository.*;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.servis.TransakcijaMapper;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class TransakcijaServisImpl implements TransakcijaServis {

    private final UplataRepository uplataRepository;
    private final PrenosSredstavaRepository prenosSredstavaRepository;
    private final SablonTransakcijeRepository sablonTransakcijeRepository;

    private final PravniRacunRepository pravniRacunRepository;
    private final TekuciRacunRepository tekuciRacunRepository;
    private final DevizniRacunRepository devizniRacunRepository;

    private final DtoOriginalMapper dtoOriginalMapper;
    private final RacunServis racunServis;

    /////////////////////////////////////////////////////////////////////////

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id) {

        if(id != null)
            return (PrenosSredstavaDTO) dtoOriginalMapper.originalToDtoWithId(prenosSredstavaRepository.findById(id).get());
        return null;
    } // TODO zameniti sa vratiPrenosSredstavaDtoPoId

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id) {
        if(id != null)
            return (UplataDTO) dtoOriginalMapper.originalToDtoWithId(uplataRepository.findById(id).get());
        return null;
    } // TODO zameniti sa vratiUplatuPoId

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca) {
        return null;
    } // TODO zameniti sa vratiPrenosSredstavaDtoPoRacunuPrimaoca koji vraca listu

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca) {
        return null;
    } // TODO zameniti sa vratiUplataDtoPoRacunuPrimaoca koji vraca listu

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca) {
        return null;
    } // TODO zameniti sa vratiPrenosSredstavaDtoPoRacunuPosiljaoca koji vraca listu

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca) {
        return null;
    } // TODO zameniti sa vratiUplataDtoPoRacunuPosiljaoca koji vraca listu

    /////////////////////////////////////////////////////////////////////////

    @Override
    public PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO) {

        switch(racunServis.nadjiVrstuRacuna(noviPrenosSredstavaDTO.getRacunPosiljaoca())) {
            case "PravniRacun":
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(noviPrenosSredstavaDTO.getRacunPosiljaoca());
                if (pravniRacun == null) return null;
                if (pravniRacun.getRaspolozivoStanje().compareTo(noviPrenosSredstavaDTO.getIznos()) < 0) return null;
                pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().subtract(noviPrenosSredstavaDTO.getIznos()));
                pravniRacunRepository.save(pravniRacun);
                break;
            case "DevizniRacun":
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(noviPrenosSredstavaDTO.getRacunPosiljaoca());
                if (devizniRacun == null) return null;
                if (devizniRacun.getRaspolozivoStanje().compareTo(noviPrenosSredstavaDTO.getIznos()) < 0) return null;
                devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().subtract(noviPrenosSredstavaDTO.getIznos()));
                devizniRacunRepository.save(devizniRacun);
                break;
            case "TekuciRacun":
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(noviPrenosSredstavaDTO.getRacunPosiljaoca());
                if (tekuciRacun == null) return null;
                if (tekuciRacun.getRaspolozivoStanje().compareTo(noviPrenosSredstavaDTO.getIznos()) < 0) return null;
                tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().subtract(noviPrenosSredstavaDTO.getIznos()));
                tekuciRacunRepository.save(tekuciRacun);
                break;
        }

        return prenosSredstavaRepository.save(TransakcijaMapper.NoviPrenosSredstavaDtoToEntity(noviPrenosSredstavaDTO));
    }

    @Override
    public Uplata sacuvajUplatu(NovaUplataDTO novaUplataDTO) {

        switch(racunServis.nadjiVrstuRacuna(novaUplataDTO.getRacunPosiljaoca())) {
            case "PravniRacun":
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(novaUplataDTO.getRacunPosiljaoca());
                if (pravniRacun == null) return null;
                if (pravniRacun.getRaspolozivoStanje().compareTo(novaUplataDTO.getIznos()) < 0) return null;
                pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().subtract(novaUplataDTO.getIznos()));
                pravniRacunRepository.save(pravniRacun);
                break;
            case "DevizniRacun":
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(novaUplataDTO.getRacunPosiljaoca());
                if (devizniRacun == null) return null;
                if (devizniRacun.getRaspolozivoStanje().compareTo(novaUplataDTO.getIznos()) < 0) return null;
                devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().subtract(novaUplataDTO.getIznos()));
                devizniRacunRepository.save(devizniRacun);
                break;
            case "TekuciRacun":
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(novaUplataDTO.getRacunPosiljaoca());
                if (tekuciRacun == null) return null;
                if (tekuciRacun.getRaspolozivoStanje().compareTo(novaUplataDTO.getIznos()) < 0) return null;
                tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().subtract(novaUplataDTO.getIznos()));
                tekuciRacunRepository.save(tekuciRacun);
                break;
        }

        return uplataRepository.save(TransakcijaMapper.NovoPlacanjeDtoToEntity(novaUplataDTO));
    }

    @Override
    public Optional<PrenosSredstava> vratiPrenosSredstavaPoId(Long id) {
        return prenosSredstavaRepository.findById(id);
    }

    @Override
    public Optional<Uplata> vratiUplatuPoId(Long id) {
        return uplataRepository.findById(id);
    }

    @Override
    public PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id) {
        return prenosSredstavaRepository.findById(id)
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .orElseThrow(() -> new EntityNotFoundException("Prenos sredstava sa ID-om " + id + " nije pronađen."));
    }

    @Override
    public UplataDTO vratiUplatuDtoPoId(Long id) {
        return uplataRepository.findById(id)
                .map(TransakcijaMapper::PlacanjeToDto)
                .orElseThrow(() -> new EntityNotFoundException("Placanje sa ID-om " + id + " nije pronađeno."));
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return prenosSredstavaRepository.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiUplataDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return uplataRepository.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return prenosSredstavaRepository.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiUplataDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return uplataRepository.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstava> vratiPrenosSredstavaUObradi() {
        return prenosSredstavaRepository.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public List<Uplata> vratiUplateUObradi() {
        return uplataRepository.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public BigDecimal izracunajRezervisanaSredstva(Long idRacuna) {

        return switch (racunServis.nadjiVrstuRacuna(idRacuna)) {
            case "PravniRacun" -> {
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(idRacuna);
                yield (pravniRacun != null) ? pravniRacun.getStanje().subtract(pravniRacun.getRaspolozivoStanje()) : null;
            }
            case "DevizniRacun" -> {
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(idRacuna);
                yield (devizniRacun != null) ? devizniRacun.getStanje().subtract(devizniRacun.getRaspolozivoStanje()) : null;
            }
            case "TekuciRacun" -> {
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(idRacuna);
                yield (tekuciRacun != null) ? tekuciRacun.getStanje().subtract(tekuciRacun.getRaspolozivoStanje()) : null;
            }
            default -> null;
        };
    }

    @Override
    public BigDecimal vratiSredstva(Long idRacuna) {

        return switch (racunServis.nadjiVrstuRacuna(idRacuna)) {
            case "PravniRacun" -> {
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(idRacuna);
                yield (pravniRacun != null) ? pravniRacun.getStanje() : null;
            }
            case "DevizniRacun" -> {
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(idRacuna);
                yield (devizniRacun != null) ? devizniRacun.getStanje() : null;
            }
            case "TekuciRacun" -> {
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(idRacuna);
                yield (tekuciRacun != null) ? tekuciRacun.getStanje() : null;
            }
            default -> null;
        };
    }

    @Override
    public Uplata promeniStatusUplate(Long idUplate, String status, Long vremeIzvrsavanja) {
        return uplataRepository.findById(idUplate)
                .map(uplata -> {
                    uplata.setStatus(status);
                    uplata.setVremeIzvrsavanja(vremeIzvrsavanja);
                    return uplataRepository.save(uplata);
                })
                .orElseThrow(() -> new EntityNotFoundException("Uplata sa ID-om " + idUplate + " nije pronađen."));
    }

    @Override
    public PrenosSredstava promeniStatusPrenosaSredstava(Long idPrenosaSredstava, String status, Long vremeIzvrsavanja) {
        return prenosSredstavaRepository.findById(idPrenosaSredstava)
                .map(prenosSredstava -> {
                    prenosSredstava.setStatus(status);
                    prenosSredstava.setVremeIzvrsavanja(vremeIzvrsavanja);
                    return prenosSredstavaRepository.save(prenosSredstava);
                })
                .orElseThrow(() -> new EntityNotFoundException("Prenos sredstava sa ID-om " + idPrenosaSredstava + " nije pronađen."));
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void realizacijaTransakcijaZaPrenostSredstava() {
        List<PrenosSredstava> prenosi = vratiPrenosSredstavaUObradi();

        for (PrenosSredstava prenosSredstava : prenosi) {

            RealizacijaTransakcije realizacijaTransakcijePosiljaoca = null;
            RealizacijaTransakcije realizacijaTransakcijePrimaoca = null;

            switch (racunServis.nadjiVrstuRacuna(prenosSredstava.getRacunPosiljaoca())) {
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(prenosSredstava.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(pravniRacun.getId(), pravniRacun.getAktivan(), izracunajRezervisanaSredstva(pravniRacun.getId()), pravniRacun.getCurrency(), "PravniRacun", pravniRacun.getStanje(), pravniRacun.getZaposleni());
                }
                case "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(prenosSredstava.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(devizniRacun.getId(), devizniRacun.getAktivan(), izracunajRezervisanaSredstva(devizniRacun.getId()), devizniRacun.getCurrency(), "DevizniRacun", devizniRacun.getStanje(), devizniRacun.getVlasnik());
                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(prenosSredstava.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(tekuciRacun.getId(), tekuciRacun.getAktivan(), izracunajRezervisanaSredstva(tekuciRacun.getId()), tekuciRacun.getCurrency(), "TekuciRacun", tekuciRacun.getStanje(), tekuciRacun.getVlasnik());
                }
            }

            switch (racunServis.nadjiVrstuRacuna(prenosSredstava.getRacunPrimaoca())) {
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(prenosSredstava.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(pravniRacun.getId(), pravniRacun.getAktivan(), izracunajRezervisanaSredstva(pravniRacun.getId()), pravniRacun.getCurrency(), "PravniRacun", pravniRacun.getStanje(), pravniRacun.getZaposleni());
                }
                case "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(prenosSredstava.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(devizniRacun.getId(), devizniRacun.getAktivan(), izracunajRezervisanaSredstva(devizniRacun.getId()), devizniRacun.getCurrency(), "DevizniRacun", devizniRacun.getStanje(), devizniRacun.getVlasnik());
                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(prenosSredstava.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(tekuciRacun.getId(), tekuciRacun.getAktivan(), izracunajRezervisanaSredstva(tekuciRacun.getId()), tekuciRacun.getCurrency(), "TekuciRacun", tekuciRacun.getStanje(), tekuciRacun.getVlasnik());
                }
            }

            if (realizacijaTransakcijePosiljaoca == null || realizacijaTransakcijePrimaoca == null){
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.NEUSPELO, System.currentTimeMillis());
                continue;
            }

            if(realizacijaTransakcijePosiljaoca.getIdKorisnika() != realizacijaTransakcijePrimaoca.getIdKorisnika()){
                neuspeoPrenos(realizacijaTransakcijePosiljaoca.getTipRacuna(), prenosSredstava);
                continue;
            }


            if(!realizacijaTransakcijePosiljaoca.isAktivan() || !realizacijaTransakcijePrimaoca.isAktivan()){
                neuspeoPrenos(realizacijaTransakcijePosiljaoca.getTipRacuna(), prenosSredstava);
                continue;
            }

            if(!proveriZajednickiElement(realizacijaTransakcijePosiljaoca.getValute().split(","), realizacijaTransakcijePrimaoca.getValute().split(","))){
                neuspeoPrenos(realizacijaTransakcijePosiljaoca.getTipRacuna(), prenosSredstava);
                continue;
            }

            if (realizacijaTransakcijePosiljaoca.getRezervisanaSredstva().compareTo(prenosSredstava.getIznos()) < 0) {
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.NEUSPELO, System.currentTimeMillis());
                continue;
            }

            boolean prosaoPrvi = false;
            boolean prosaoDrugi = false;

            switch (realizacijaTransakcijePosiljaoca.getTipRacuna()){
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    pravniRacun.setStanje(pravniRacun.getStanje().subtract(prenosSredstava.getIznos()));
                    PravniRacun racun = pravniRacunRepository.save(pravniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(prenosSredstava.getIznos())) == 0){
                        prosaoPrvi = true;
                    }
                }
                case  "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    devizniRacun.setStanje(devizniRacun.getStanje().subtract(prenosSredstava.getIznos()));
                    DevizniRacun racun = devizniRacunRepository.save(devizniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(prenosSredstava.getIznos())) == 0){
                        prosaoPrvi = true;
                    }                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    tekuciRacun.setStanje(tekuciRacun.getStanje().subtract(prenosSredstava.getIznos()));
                    TekuciRacun racun = tekuciRacunRepository.save(tekuciRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(prenosSredstava.getIznos())) == 0){
                        prosaoPrvi = true;
                    }
                }
            }

            switch (realizacijaTransakcijePrimaoca.getTipRacuna()){
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    pravniRacun.setStanje(pravniRacun.getStanje().add(prenosSredstava.getIznos()));
                    pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                    PravniRacun racun = pravniRacunRepository.save(pravniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(prenosSredstava.getIznos())) == 0){
                        prosaoDrugi = true;
                    }
                }
                case  "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    devizniRacun.setStanje(devizniRacun.getStanje().add(prenosSredstava.getIznos()));
                    devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                    DevizniRacun racun = devizniRacunRepository.save(devizniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(prenosSredstava.getIznos())) == 0){
                        prosaoDrugi = true;
                    }

                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    tekuciRacun.setStanje(tekuciRacun.getStanje().add(prenosSredstava.getIznos()));
                    tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                    TekuciRacun racun = tekuciRacunRepository.save(tekuciRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(prenosSredstava.getIznos())) == 0){
                        prosaoDrugi = true;
                    }
                }
            }

            if(prosaoPrvi && prosaoDrugi){
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.REALIZOVANO, System.currentTimeMillis());
            } else {

                switch (realizacijaTransakcijePosiljaoca.getTipRacuna()){
                    case "PravniRacun" -> {
                        PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        pravniRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        pravniRacunRepository.save(pravniRacun);
                    }
                    case  "DevizniRacun" -> {
                        DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        devizniRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        devizniRacunRepository.save(devizniRacun);
                    }
                    case "TekuciRacun" -> {
                        TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        tekuciRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        tekuciRacunRepository.save(tekuciRacun);
                    }
                }

                neuspeoPrenos(realizacijaTransakcijePosiljaoca.getTipRacuna(), prenosSredstava);


                switch (realizacijaTransakcijePrimaoca.getTipRacuna()){
                    case "PravniRacun" -> {
                        PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        pravniRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        pravniRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        pravniRacunRepository.save(pravniRacun);

                    }
                    case  "DevizniRacun" -> {
                        DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        devizniRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        devizniRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        devizniRacunRepository.save(devizniRacun);

                    }
                    case "TekuciRacun" -> {
                        TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        tekuciRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        tekuciRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        tekuciRacunRepository.save(tekuciRacun);
                    }
                }
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.REALIZOVANO, System.currentTimeMillis());
            }
        }


    }

    @Scheduled(cron = "0 */15 * * * *")
    public void realizacijaTransakcijaZaUplatu() {
        List<Uplata> uplate = vratiUplateUObradi();

        for (Uplata uplata : uplate) {
            RealizacijaTransakcije realizacijaTransakcijePosiljaoca = null;
            RealizacijaTransakcije realizacijaTransakcijePrimaoca = null;

            switch (racunServis.nadjiVrstuRacuna(uplata.getRacunPosiljaoca())) {
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(uplata.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(pravniRacun.getId(), pravniRacun.getAktivan(), izracunajRezervisanaSredstva(pravniRacun.getId()), pravniRacun.getCurrency(), "PravniRacun", pravniRacun.getStanje(), pravniRacun.getZaposleni());
                }
                case "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(uplata.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(devizniRacun.getId(), devizniRacun.getAktivan(), izracunajRezervisanaSredstva(devizniRacun.getId()), devizniRacun.getCurrency(), "DevizniRacun", devizniRacun.getStanje(), devizniRacun.getVlasnik());
                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(uplata.getRacunPosiljaoca());
                    realizacijaTransakcijePosiljaoca = new RealizacijaTransakcije(tekuciRacun.getId(), tekuciRacun.getAktivan(), izracunajRezervisanaSredstva(tekuciRacun.getId()), tekuciRacun.getCurrency(), "TekuciRacun", tekuciRacun.getStanje(), tekuciRacun.getVlasnik());
                }
            }

            switch (racunServis.nadjiVrstuRacuna(uplata.getRacunPrimaoca())) {
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(uplata.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(pravniRacun.getId(), pravniRacun.getAktivan(), izracunajRezervisanaSredstva(pravniRacun.getId()), pravniRacun.getCurrency(), "PravniRacun", pravniRacun.getStanje(), pravniRacun.getZaposleni());
                }
                case "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(uplata.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(devizniRacun.getId(), devizniRacun.getAktivan(), izracunajRezervisanaSredstva(devizniRacun.getId()), devizniRacun.getCurrency(), "DevizniRacun", devizniRacun.getStanje(), devizniRacun.getVlasnik());
                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(uplata.getRacunPrimaoca());
                    realizacijaTransakcijePrimaoca = new RealizacijaTransakcije(tekuciRacun.getId(), tekuciRacun.getAktivan(), izracunajRezervisanaSredstva(tekuciRacun.getId()), tekuciRacun.getCurrency(), "TekuciRacun", tekuciRacun.getStanje(), tekuciRacun.getVlasnik());
                }
            }

            if (realizacijaTransakcijePosiljaoca == null || realizacijaTransakcijePrimaoca == null){
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
                continue;
            }

            if(!realizacijaTransakcijePosiljaoca.isAktivan() || !realizacijaTransakcijePrimaoca.isAktivan()){
                neuspelaUplata(realizacijaTransakcijePosiljaoca.getTipRacuna(), uplata);
                continue;
            }

            if(!proveriZajednickiElement(realizacijaTransakcijePosiljaoca.getValute().split(","), realizacijaTransakcijePrimaoca.getValute().split(","))){
                neuspelaUplata(realizacijaTransakcijePosiljaoca.getTipRacuna(), uplata);
                continue;
            }

            if (realizacijaTransakcijePosiljaoca.getRezervisanaSredstva().compareTo(uplata.getIznos()) < 0) {
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
                continue;
            }

            boolean prosaoPrvi = false;
            boolean prosaoDrugi = false;

            switch (realizacijaTransakcijePosiljaoca.getTipRacuna()){
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    pravniRacun.setStanje(pravniRacun.getStanje().subtract(uplata.getIznos()));
                    PravniRacun racun = pravniRacunRepository.save(pravniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(uplata.getIznos())) == 0){
                        prosaoPrvi = true;
                    }
                }
                case  "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    devizniRacun.setStanje(devizniRacun.getStanje().subtract(uplata.getIznos()));
                    DevizniRacun racun = devizniRacunRepository.save(devizniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(uplata.getIznos())) == 0){
                        prosaoPrvi = true;
                    }                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                    tekuciRacun.setStanje(tekuciRacun.getStanje().subtract(uplata.getIznos()));
                    TekuciRacun racun = tekuciRacunRepository.save(tekuciRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().subtract(uplata.getIznos())) == 0){
                        prosaoPrvi = true;
                    }
                }
            }

            switch (realizacijaTransakcijePrimaoca.getTipRacuna()){
                case "PravniRacun" -> {
                    PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    pravniRacun.setStanje(pravniRacun.getStanje().add(uplata.getIznos()));
                    pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                    PravniRacun racun = pravniRacunRepository.save(pravniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(uplata.getIznos())) == 0){
                        prosaoDrugi = true;
                    }
                }
                case  "DevizniRacun" -> {
                    DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    devizniRacun.setStanje(devizniRacun.getStanje().add(uplata.getIznos()));
                    devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                    DevizniRacun racun = devizniRacunRepository.save(devizniRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(uplata.getIznos())) == 0){
                        prosaoDrugi = true;
                    }

                }
                case "TekuciRacun" -> {
                    TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                    tekuciRacun.setStanje(tekuciRacun.getStanje().add(uplata.getIznos()));
                    tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                    TekuciRacun racun = tekuciRacunRepository.save(tekuciRacun);
                    if(racun.getStanje().compareTo(realizacijaTransakcijePosiljaoca.getPrethodnoStanje().add(uplata.getIznos())) == 0){
                        prosaoDrugi = true;
                    }
                }
            }

            if(prosaoPrvi && prosaoDrugi){
                promeniStatusUplate(uplata.getId(), Status.REALIZOVANO, System.currentTimeMillis());
            } else {

                switch (realizacijaTransakcijePosiljaoca.getTipRacuna()){
                    case "PravniRacun" -> {
                        PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        pravniRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        pravniRacunRepository.save(pravniRacun);
                    }
                    case  "DevizniRacun" -> {
                        DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        devizniRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        devizniRacunRepository.save(devizniRacun);
                                    }
                    case "TekuciRacun" -> {
                        TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePosiljaoca.getBrojRacuna());
                        tekuciRacun.setStanje(realizacijaTransakcijePosiljaoca.getPrethodnoStanje());
                        tekuciRacunRepository.save(tekuciRacun);
                    }
                }

                neuspelaUplata(realizacijaTransakcijePosiljaoca.getTipRacuna(), uplata);


                switch (realizacijaTransakcijePrimaoca.getTipRacuna()){
                    case "PravniRacun" -> {
                        PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        pravniRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        pravniRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        pravniRacunRepository.save(pravniRacun);

                    }
                    case  "DevizniRacun" -> {
                        DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        devizniRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        devizniRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        devizniRacunRepository.save(devizniRacun);

                    }
                    case "TekuciRacun" -> {
                        TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(realizacijaTransakcijePrimaoca.getBrojRacuna());
                        tekuciRacun.setStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje());
                        tekuciRacun.setRaspolozivoStanje(realizacijaTransakcijePrimaoca.getPrethodnoStanje().subtract(realizacijaTransakcijePrimaoca.getRezervisanaSredstva()));
                        tekuciRacunRepository.save(tekuciRacun);
                    }
                }
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
        }
    }

    private void neuspelaUplata(String tip, Uplata uplata){
        switch (tip){
            case "PravniRacun" -> {
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(uplata.getRacunPosiljaoca());
                pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
            case  "DevizniRacun" -> {
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(uplata.getRacunPosiljaoca());
                devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
            case "TekuciRacun" -> {
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(uplata.getRacunPosiljaoca());
                tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().add(uplata.getIznos()));
                promeniStatusUplate(uplata.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
        }
    }

    private void neuspeoPrenos(String tip, PrenosSredstava prenosSredstava){
        switch (tip){
            case "PravniRacun" -> {
                PravniRacun pravniRacun = racunServis.nadjiAktivanPravniRacunPoID(prenosSredstava.getRacunPosiljaoca());
                pravniRacun.setRaspolozivoStanje(pravniRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
            case  "DevizniRacun" -> {
                DevizniRacun devizniRacun = racunServis.nadjiAktivanDevizniRacunPoID(prenosSredstava.getRacunPosiljaoca());
                devizniRacun.setRaspolozivoStanje(devizniRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
            case "TekuciRacun" -> {
                TekuciRacun tekuciRacun = racunServis.nadjiAktivanTekuciRacunPoID(prenosSredstava.getRacunPosiljaoca());
                tekuciRacun.setRaspolozivoStanje(tekuciRacun.getRaspolozivoStanje().add(prenosSredstava.getIznos()));
                promeniStatusPrenosaSredstava(prenosSredstava.getId(), Status.NEUSPELO, System.currentTimeMillis());
            }
        }
    }

    private boolean proveriZajednickiElement(String[] niz1, String[] niz2) {
        for (int i = 0; i < niz1.length; i++) {
            for (int j = 0; j < niz2.length; j++) {
                if (niz1[i].equals(niz2[j])) {
                    return true;
                }
            }
        }
        return false;
    }



    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////


    public List<Object> getAllTransactionsByKorisnikId(Long clientId) {


        List<Object> allTransactions = new ArrayList<>();

        if(clientId != null) {
            List<Object> allPravniRacunKlijenta;
            List<Object> allTekuciRacunKlijenta;
            List<Object> allDevizniRacunKlijenta;

            /*
        allPravniRacunKlijenta = pravniRacunRepository.findAllBillsByClientId(clientId);
        allTekuciRacunKlijenta = tekuciRacunRepository.findAllBillsByClientId(clientId);
        allDevizniRacunKlijenta = devizniRacunRepository.findAllBillsByClientId(clientId);

        for(Object clientBill:allPravniRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((PravniRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((PravniRacun)clientBill).getId()));
        }
        for(Object clientBill:allTekuciRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((TekuciRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((TekuciRacun)clientBill).getId()));
        }
        for(Object clientBill:allDevizniRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((DevizniRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((DevizniRacun)clientBill).getId()));
        }*/
            return allTransactions;
        }
        return null;
    }

    @Override
    public boolean proveraIspravnostiUplataTransakcije(Uplata uplata) {

        return false;
    }

    @Override
    public boolean proveraIspravnostiPrenosSredstavaTransakcije(PrenosSredstava prenosSredstava) {
        return false;
    }

    @Override
    public List<SablonTransakcije> getSavedTransactionalPatterns() {
        return this.sablonTransakcijeRepository.findAll();
    }

    @Override
    public SablonTransakcije addNewTransactionalPattern(SablonTransakcije sablonTransakcije) {
        if(sablonTransakcije != null) {           //AKO VEC POSTOJI AZURIRACE,AKO NE POSTOJI DODACE
            return this.sablonTransakcijeRepository.save(sablonTransakcije);
        }
        return null;
    }

    @Override
    public boolean deleteTransactionalPattern(Long transactionPatternId) {

        if(transactionPatternId != null){
                                        //NE VRACA GRESKU AKO NE POSTOJI ID
         this.sablonTransakcijeRepository.deleteById(transactionPatternId);
         return true;
        }
        return false;
    }

    @Override
    public void deleteAllTransactionalPatterns() {
                                        //NE VRACA GRESKU AKO NEMA STA DA SE BRISE
        this.sablonTransakcijeRepository.deleteAll();
    }

}
