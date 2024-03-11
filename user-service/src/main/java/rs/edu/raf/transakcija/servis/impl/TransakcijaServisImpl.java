package rs.edu.raf.transakcija.servis.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.repo.PlacanjaRepozitorijum;
import rs.edu.raf.transakcija.repo.PrenosSredstavaRepozitorijum;
import rs.edu.raf.transakcija.servis.TransakcijaMapper;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransakcijaServisImpl implements TransakcijaServis {

    private final PlacanjaRepozitorijum placanjaRepozitorijum;
    private final PrenosSredstavaRepozitorijum prenosSredstavaRepozitorijum;

    @Autowired
    public TransakcijaServisImpl(PlacanjaRepozitorijum placanjaRepozitorijum, PrenosSredstavaRepozitorijum prenosSredstavaRepozitorijum) {
        this.placanjaRepozitorijum = placanjaRepozitorijum;
        this.prenosSredstavaRepozitorijum = prenosSredstavaRepozitorijum;
    }

    @Override
    public PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO) {
        return prenosSredstavaRepozitorijum.save(TransakcijaMapper.NoviPrenosSredstavaDtoToEntity(noviPrenosSredstavaDTO));
    }

    @Override
    public Uplata sacuvajPlacanje(NovaUplataDTO novaUplataDTO) {
        return placanjaRepozitorijum.save(TransakcijaMapper.NovoPlacanjeDtoToEntity(novaUplataDTO));
    }

    @Override
    public Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id);
    }

    @Override
    public Optional<Uplata> nadjiPlacanjePoId(Long id) {
        return placanjaRepozitorijum.findById(id);
    }

    @Override
    public PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id)
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .orElseThrow(() -> new EntityNotFoundException("Prenos sredstava sa ID-om " + id + " nije pronađen."));
    }

    @Override
    public UplataDTO vratiPlacanjeDtoPoId(Long id) {
        return placanjaRepozitorijum.findById(id)
                .map(TransakcijaMapper::PlacanjeToDto)
                .orElseThrow(() -> new EntityNotFoundException("Placanje sa ID-om " + id + " nije pronađeno."));
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return prenosSredstavaRepozitorijum.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return placanjaRepozitorijum.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return prenosSredstavaRepozitorijum.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return placanjaRepozitorijum.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstava> vratiPrenosSredstavaUObradi() {
        return prenosSredstavaRepozitorijum.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public List<Uplata> vratiPlacanjaUObradi() {
        return placanjaRepozitorijum.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public String izracunajRezervisaneResurse(Long idRacuna) {
        return null;
    }

    @Override
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa) {
        return false;
    }


}
