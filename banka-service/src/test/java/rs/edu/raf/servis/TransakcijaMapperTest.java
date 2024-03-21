package rs.edu.raf.servis;

import org.junit.jupiter.api.Test;
import rs.edu.raf.model.dto.transaction.NovaUplataDTO;
import rs.edu.raf.model.dto.transaction.NoviPrenosSredstavaDTO;
import rs.edu.raf.model.dto.transaction.PrenosSredstavaDTO;
import rs.edu.raf.model.dto.transaction.UplataDTO;
import rs.edu.raf.model.entities.transaction.PrenosSredstava;
import rs.edu.raf.model.entities.transaction.Status;
import rs.edu.raf.model.entities.transaction.Uplata;

import java.math.BigDecimal;

class TransakcijaMapperTest {

    @Test
    void shouldMapNovoPlacanjeDtoToEntity() {
        NovaUplataDTO novaUplataDTO = new NovaUplataDTO();
        novaUplataDTO.setRacunPosiljaoca(123456789L);
        novaUplataDTO.setNazivPrimaoca("John Doe");
        novaUplataDTO.setRacunPrimaoca(987654321L);
        novaUplataDTO.setIznos(new BigDecimal("1000.0"));
        novaUplataDTO.setPozivNaBroj(123456);
        novaUplataDTO.setSifraPlacanja(789456);
        novaUplataDTO.setSvrhaPlacanja("Payment for services");

        Uplata uplata = TransakcijaMapper.NovoPlacanjeDtoToEntity(novaUplataDTO);

        assertEquals(novaUplataDTO.getRacunPosiljaoca(), uplata.getRacunPosiljaoca());
        assertEquals(novaUplataDTO.getNazivPrimaoca(), uplata.getNazivPrimaoca());
        assertEquals(novaUplataDTO.getRacunPrimaoca(), uplata.getRacunPrimaoca());
        assertEquals(novaUplataDTO.getIznos(), uplata.getIznos());
        assertEquals(novaUplataDTO.getPozivNaBroj(), uplata.getPozivNaBroj());
        assertEquals(novaUplataDTO.getSifraPlacanja(), uplata.getSifraPlacanja());
        assertEquals(novaUplataDTO.getSvrhaPlacanja(), uplata.getSvrhaPlacanja());
        assertEquals(Status.U_OBRADI, uplata.getStatus());
    }

    @Test
    void shouldMapNoviPrenosSredstavaDtoToEntity() {
        NoviPrenosSredstavaDTO noviPrenosSredstavaDTO = new NoviPrenosSredstavaDTO();
        noviPrenosSredstavaDTO.setRacunPosiljaoca(123456789L);
        noviPrenosSredstavaDTO.setRacunPrimaoca(987654321L);
        noviPrenosSredstavaDTO.setIznos(new BigDecimal("1000.0"));

        PrenosSredstava prenosSredstava = TransakcijaMapper.NoviPrenosSredstavaDtoToEntity(noviPrenosSredstavaDTO);

        assertEquals(noviPrenosSredstavaDTO.getRacunPosiljaoca(), prenosSredstava.getRacunPosiljaoca());
        assertEquals(noviPrenosSredstavaDTO.getRacunPrimaoca(), prenosSredstava.getRacunPrimaoca());
        assertEquals(noviPrenosSredstavaDTO.getIznos(), prenosSredstava.getIznos());
        assertEquals(Status.U_OBRADI, prenosSredstava.getStatus());
    }

    @Test
    void shouldMapPlacanjeToDto() {
        Uplata uplata = new Uplata();
        uplata.setRacunPosiljaoca(123456789L);
        uplata.setNazivPrimaoca("John Doe");
        uplata.setRacunPrimaoca(987654321L);
        uplata.setIznos(new BigDecimal("1000.0"));
        uplata.setPozivNaBroj(123456);
        uplata.setSifraPlacanja(789456);
        uplata.setSvrhaPlacanja("Payment for services");
        uplata.setStatus(Status.U_OBRADI);
        uplata.setVremeTransakcije(System.currentTimeMillis());

        UplataDTO dto = TransakcijaMapper.PlacanjeToDto(uplata);

        assertEquals(uplata.getRacunPosiljaoca(), dto.getRacunPosiljaoca());
        assertEquals(uplata.getNazivPrimaoca(), dto.getNazivPrimaoca());
        assertEquals(uplata.getRacunPrimaoca(), dto.getRacunPrimaoca());
        assertEquals(uplata.getIznos(), dto.getIznos());
        assertEquals(uplata.getPozivNaBroj(), dto.getPozivNaBroj());
        assertEquals(uplata.getSifraPlacanja(), dto.getSifraPlacanja());
        assertEquals(uplata.getSvrhaPlacanja(), dto.getSvrhaPlacanja());
        assertEquals(uplata.getStatus(), dto.getStatus());
        assertEquals(uplata.getVremeTransakcije(), dto.getVremeTransakcije());
    }

    @Test
    void shouldMapPrenosSredstavaToDto() {
        PrenosSredstava prenosSredstava = new PrenosSredstava();
        prenosSredstava.setRacunPosiljaoca(123456789L);
        prenosSredstava.setRacunPrimaoca(987654321L);
        prenosSredstava.setIznos(new BigDecimal("1000.0"));
        prenosSredstava.setVreme(System.currentTimeMillis());
        prenosSredstava.setStatus(Status.U_OBRADI);

        PrenosSredstavaDTO dto = TransakcijaMapper.PrenosSredstavaToDto(prenosSredstava);

        assertEquals(prenosSredstava.getRacunPosiljaoca(), dto.getPrviRacun());
        assertEquals(prenosSredstava.getRacunPrimaoca(), dto.getDrugiRacun());
        assertEquals(prenosSredstava.getIznos(), dto.getIznos());
        assertEquals(prenosSredstava.getVreme(), dto.getVreme());
        assertEquals(prenosSredstava.getStatus(), dto.getStatus());
    }
}