package rs.edu.raf.racun.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.racun.dto.FirmaDTO;
import rs.edu.raf.racun.dto.NovaFirmaDTO;
import rs.edu.raf.racun.model.Firma;

@Component
public class FirmaMapper {

    public FirmaDTO firmaToFirmaDTO(Firma f) {
        FirmaDTO dto = new FirmaDTO();
        dto.setNazivPreduzeca(f.getNazivPreduzeca());
        dto.setPovezaniRacuni(f.getPovezaniRacuni());
        dto.setBrojTelefona(f.getBrojTelefona());
        dto.setBrojFaksa(f.getBrojFaksa());
        dto.setPIB(f.getPIB());
        dto.setMaticniBroj(f.getMaticniBroj());
        dto.setSifraDelatnosti(f.getSifraDelatnosti());
        dto.setRegistarskiBroj(f.getRegistarskiBroj());
        return dto;
    }

    public Firma novaFirmaDTOToFirma(NovaFirmaDTO dto) {
        Firma f = new Firma();
        f.setNazivPreduzeca(dto.getNazivPreduzeca());
        f.setBrojTelefona(dto.getBrojTelefona());
        f.setBrojFaksa(dto.getBrojFaksa());
        f.setPIB(dto.getPIB());
        f.setMaticniBroj(dto.getMaticniBroj());
        f.setSifraDelatnosti(dto.getSifraDelatnosti());
        f.setRegistarskiBroj(dto.getRegistarskiBroj());
        return f;
    }
}
