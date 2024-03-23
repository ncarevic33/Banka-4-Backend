package rs.edu.raf.berza.opcija.servis;

import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.model.KorisnikoveKupljeneOpcije;
import rs.edu.raf.berza.opcija.model.Opcija;
import rs.edu.raf.berza.opcija.model.OpcijaStanje;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface OpcijaServis {

        List<OpcijaDto> findAll() throws InterruptedException;

        OpcijaDto save(NovaOpcijaDto option);

        Opcija findById(Long id);

        void azuirajPostojeceOpcije() throws IOException;

        List<OpcijaDto> findByStockAndDateAndStrike(String ticker, LocalDateTime datumIstekaVazenja, Double strikePrice);

        KorisnikoveKupljeneOpcije izvrsiOpciju(Long opcijaId, Long userId);

        OpcijaStanje proveriStanjeOpcije(Long opcijaId);

}
