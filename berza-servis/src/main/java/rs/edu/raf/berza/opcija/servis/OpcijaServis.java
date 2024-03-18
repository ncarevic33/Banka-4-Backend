package rs.edu.raf.berza.opcija.servis;

import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.model.Opcija;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpcijaServis {

        List<OpcijaDto> findAll() throws InterruptedException;

        OpcijaDto save(NovaOpcijaDto option);

        Optional<Opcija> findById(Long id);

        void azuirajPostojeceOpcije() throws IOException;

        List<OpcijaDto> findByStockAndDateAndStrike(String ticker, LocalDateTime datumIstekaVazenja, double strikePrice);

        boolean izvrsiOpciju(Long opcijaId);

        OpcijaDto proveriStanjeOpcije(Long opcijaId);

}
