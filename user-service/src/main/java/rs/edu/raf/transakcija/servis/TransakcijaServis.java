package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovoPlacanjeDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.PlacanjeDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Placanje;

import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {

    PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO);

    Placanje sacuvajPlacanje(NovoPlacanjeDTO novoPlacanjeDTO);

    Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id);

    Optional<Placanje> nadjiPlacanjePoId(Long id);

     PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id);

     PlacanjeDTO vratiPlacanjeDtoPoId(Long id);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<PlacanjeDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<PrenosSredstava> vratiPrenosSredstavaUObradi();

     List<Placanje> vratiPlacanjaUObradi();

     String izracunajRezervisaneResurse(Long idRacuna);

     boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);

}
