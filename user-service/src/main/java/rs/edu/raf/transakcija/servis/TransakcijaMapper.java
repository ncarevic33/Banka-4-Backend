package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;

public class TransakcijaMapper {

    public static Uplata NovoPlacanjeDtoToEntity(NovaUplataDTO novaUplataDTO) {
        Uplata uplata = new Uplata();
        uplata.setRacunPosiljaoca(novaUplataDTO.getRacunPosiljaoca());
        uplata.setNazivPrimaoca(novaUplataDTO.getNazivPrimaoca());
        uplata.setRacunPrimaoca(novaUplataDTO.getRacunPrimaoca());
        uplata.setIznos(novaUplataDTO.getIznos());
        uplata.setPozivNaBroj(novaUplataDTO.getPozivNaBroj());
        uplata.setSifraPlacanja(novaUplataDTO.getSifraPlacanja());
        uplata.setSvrhaPlacanja(novaUplataDTO.getSvrhaPlacanja());
        uplata.setStatus(Status.U_OBRADI);
        uplata.setVremeTransakcije(System.currentTimeMillis());
        return uplata;
    }

    public static UplataDTO PlacanjeToDto(Uplata uplata) {
        UplataDTO dto = new UplataDTO();
        dto.setRacunPosiljaoca(uplata.getRacunPosiljaoca());
        dto.setNazivPrimaoca(uplata.getNazivPrimaoca());
        dto.setRacunPrimaoca(uplata.getRacunPrimaoca());
        dto.setIznos(uplata.getIznos());
        dto.setPozivNaBroj(uplata.getPozivNaBroj());
        dto.setSifraPlacanja(uplata.getSifraPlacanja());
        dto.setSvrhaPlacanja(uplata.getSvrhaPlacanja());
        dto.setStatus(uplata.getStatus());
        dto.setVremeTransakcije(uplata.getVremeTransakcije());
        dto.setVremeIzvrsavanja(uplata.getVremeIzvrsavanja());
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
