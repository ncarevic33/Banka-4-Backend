package rs.edu.raf.transakcija.servis;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovoPlacanjeDTO;
import rs.edu.raf.transakcija.dto.PlacanjeDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.model.Placanje;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.repo.PlacanjaRepozitorijum;
import rs.edu.raf.transakcija.repo.PrenosSredstavaRepozitorijum;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransakcijaServisImpl implements TransakcijaServis{

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
    public Placanje sacuvajPlacanje(NovoPlacanjeDTO novoPlacanjeDTO) {
        return placanjaRepozitorijum.save(TransakcijaMapper.NovoPlacanjeDtoToEntity(novoPlacanjeDTO));
    }

    @Override
    public Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id);
    }

    @Override
    public Optional<Placanje> nadjiPlacanjePoId(Long id) {
        return placanjaRepozitorijum.findById(id);
    }

    @Override
    public PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id)
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .orElseThrow(() -> new EntityNotFoundException("Prenos sredstava sa ID-om " + id + " nije pronađen."));
    }

    @Override
    public PlacanjeDTO vratiPlacanjeDtoPoId(Long id) {
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
    public List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca) {
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
    public List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return placanjaRepozitorijum.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstava> vratiPrenosSredstavaUObradi() {
        return prenosSredstavaRepozitorijum.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public List<Placanje> vratiPlacanjaUObradi() {
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
