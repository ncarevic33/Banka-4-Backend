package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {

    PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO);

    Uplata sacuvajPlacanje(NovaUplataDTO novaUplataDTO);

    Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id);

    Optional<Uplata> nadjiPlacanjePoId(Long id);

     PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id);

     UplataDTO vratiPlacanjeDtoPoId(Long id);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<UplataDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<UplataDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<PrenosSredstava> vratiPrenosSredstavaUObradi();

     List<Uplata> vratiPlacanjaUObradi();

     String izracunajRezervisaneResurse(Long idRacuna);

     boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);

}
