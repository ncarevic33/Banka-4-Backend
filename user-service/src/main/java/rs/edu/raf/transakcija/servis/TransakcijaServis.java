package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.PlacanjeDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Placanje;

import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {

    public Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id);

    public Optional<Placanje> nadjiPlacanjePoId(Long id);

    public PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id);

    public PlacanjeDTO vratiPlacanjeDtoPoId(Long id);

    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

    public List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca);

    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    public List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    public List<PrenosSredstava> vratiPrenosSredstavaUObradi();

    public List<Placanje> vratiPlacanjaUObradi();

    public String izracunajRezervisaneResurse(Long idRacuna);

    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);

}
