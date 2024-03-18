package rs.edu.raf.korisnik.servis.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.model.Kod;
import rs.edu.raf.korisnik.repository.KodRepository;
import rs.edu.raf.korisnik.servis.KodServis;

import java.util.Optional;

@Service
@AllArgsConstructor
public class KodServisImpl implements KodServis {

    private KodRepository kodRepository;

    @Override
    public void dodajKod(String email, String kod, Long expirationDate, boolean reset) {

                                //postoji kod za reset i kod pre login, a razlikuju se po boolean reset
        kodRepository.save(new Kod(email,kod,expirationDate,reset));
    }

    @Override
    public boolean dobarKod(String email, String kod, boolean reset) {
        Optional<Kod> k = kodRepository.findByEmailAndReset(email, reset);
        if(k.isPresent()) {
            if(k.get().getCode().equals(kod) && k.get().getExpirationDate() >= System.currentTimeMillis()) {
                kodRepository.delete(k.get());
                return true;
            }
        }
        return false;
    }
}
