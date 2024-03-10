package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovoPlacanjeDTO;
import rs.edu.raf.transakcija.dto.PlacanjeDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.model.Placanje;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;

public class TransakcijaMapper {

    public static Placanje NovoPlacanjeDtoToEntity(NovoPlacanjeDTO novoPlacanjeDTO) {
        Placanje placanje = new Placanje();
        placanje.setRacunPosiljaoca(novoPlacanjeDTO.getRacunPosiljaoca());
        placanje.setNazivPrimaoca(novoPlacanjeDTO.getNazivPrimaoca());
        placanje.setRacunPrimaoca(novoPlacanjeDTO.getRacunPrimaoca());
        placanje.setIznos(novoPlacanjeDTO.getIznos());
        placanje.setPozivNaBroj(novoPlacanjeDTO.getPozivNaBroj());
        placanje.setSifraPlacanja(novoPlacanjeDTO.getSifraPlacanja());
        placanje.setSvrhaPlacanja(novoPlacanjeDTO.getSvrhaPlacanja());
        placanje.setStatus(Status.U_OBRADI);
        placanje.setVremeTransakcije(System.currentTimeMillis());
        return placanje;
    }

    public static PlacanjeDTO PlacanjeToDto(Placanje placanje) {
        PlacanjeDTO dto = new PlacanjeDTO();
        dto.setRacunPosiljaoca(placanje.getRacunPosiljaoca());
        dto.setNazivPrimaoca(placanje.getNazivPrimaoca());
        dto.setRacunPrimaoca(placanje.getRacunPrimaoca());
        dto.setIznos(placanje.getIznos());
        dto.setPozivNaBroj(placanje.getPozivNaBroj());
        dto.setSifraPlacanja(placanje.getSifraPlacanja());
        dto.setSvrhaPlacanja(placanje.getSvrhaPlacanja());
        dto.setStatus(placanje.getStatus());
        dto.setVremeTransakcije(placanje.getVremeTransakcije());
        dto.setVremeIzvrsavanja(placanje.getVremeIzvrsavanja());
        return dto;
    }

    public static PrenosSredstava NoviPrenosSredstavaDtoToEntity(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO) {
        PrenosSredstava prenosSredstava = new PrenosSredstava();
        prenosSredstava.setRacunPosiljaoca(noviPrenosSredstavaDTO.getRacunPosiljaoca());
        prenosSredstava.setRacunPrimaoca(noviPrenosSredstavaDTO.getRacunPrimaoca());
        prenosSredstava.setIznos(noviPrenosSredstavaDTO.getIznos());
        prenosSredstava.setVreme(System.currentTimeMillis());
        prenosSredstava.setStatus(Status.U_OBRADI);
        return prenosSredstava;
    }


    public static PrenosSredstavaDTO PrenosSredstavaToDto(PrenosSredstava prenosSredstava) {
        PrenosSredstavaDTO dto = new PrenosSredstavaDTO();
        dto.setPrviRacun(prenosSredstava.getRacunPosiljaoca());
        dto.setDrugiRacun(prenosSredstava.getRacunPrimaoca());
        dto.setIznos(prenosSredstava.getIznos());
        dto.setVreme(prenosSredstava.getVreme());
        dto.setStatus(prenosSredstava.getStatus());
        dto.setVremeIzvrsavanja(prenosSredstava.getVremeIzvrsavanja());
        return dto;
    }
}
