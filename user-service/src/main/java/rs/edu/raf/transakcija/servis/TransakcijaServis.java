package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSretstava;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;

public interface TransakcijaServis {
    public PrenosSretstava nadjiPrenosSretstavaPoId(Long id);
    public Uplata nadjiUplatuPoId(Long id);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id);
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    public List<PrenosSretstava> nadjiPrenosSretstavaKojiSuUObradi();
    public List<Uplata> nadjiUplateKojiSuUObradi();
    public String izracunajRezervisaneResurse(Long idRacuna);
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);
}
