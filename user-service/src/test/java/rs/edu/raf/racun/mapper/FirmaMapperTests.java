package rs.edu.raf.racun.mapper;

import org.junit.Test;
import rs.edu.raf.racun.dto.FirmaDTO;
import rs.edu.raf.racun.dto.NovaFirmaDTO;
import rs.edu.raf.racun.model.Firma;

import static org.junit.Assert.assertEquals;

public class FirmaMapperTests {
    
    static FirmaMapper fm = new FirmaMapper();

    @Test
    public void firmaToFirmaDTOTest() {

        Firma f = new Firma("Belit d.o.o. Beograd", "444000000900000022", "0112030403", "0112030402", 101017533, 17328905, 6102, 130501701);

        FirmaDTO dto = fm.firmaToFirmaDTO(f);

        assertEquals(dto.getId(), f.getId());
        assertEquals(dto.getNazivPreduzeca(), f.getNazivPreduzeca());
        assertEquals(dto.getPovezaniRacuni(), f.getPovezaniRacuni());
        assertEquals(dto.getBrojTelefona(), f.getBrojTelefona());
        assertEquals(dto.getBrojFaksa(), f.getBrojFaksa());
        assertEquals(dto.getPIB(), f.getPIB());
        assertEquals(dto.getMaticniBroj(), f.getMaticniBroj());
        assertEquals(dto.getSifraDelatnosti(), f.getSifraDelatnosti());
        assertEquals(dto.getRegistarskiBroj(), f.getRegistarskiBroj());
    }

    @Test
    public void novaFirmaDTOToFirmaTest() {
        NovaFirmaDTO dto = new NovaFirmaDTO();
        dto.setNazivPreduzeca("Belit d.o.o. Beograd");
        dto.setBrojTelefona("0112030403");
        dto.setBrojFaksa("0112030404");
        dto.setPIB(101017533);
        dto.setMaticniBroj(17328905);
        dto.setSifraDelatnosti(6102);
        dto.setRegistarskiBroj(130501701);
        
        Firma f = fm.novaFirmaDTOToFirma(dto);

        assertEquals(dto.getNazivPreduzeca(), f.getNazivPreduzeca());
        assertEquals("", f.getPovezaniRacuni());
        assertEquals(dto.getBrojTelefona(), f.getBrojTelefona());
        assertEquals(dto.getBrojFaksa(), f.getBrojFaksa());
        assertEquals(dto.getPIB(), f.getPIB());
        assertEquals(dto.getMaticniBroj(), f.getMaticniBroj());
        assertEquals(dto.getSifraDelatnosti(), f.getSifraDelatnosti());
        assertEquals(dto.getRegistarskiBroj(), f.getRegistarskiBroj());
    }
}
