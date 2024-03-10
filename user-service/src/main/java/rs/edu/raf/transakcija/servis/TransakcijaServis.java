package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.PlacanjaDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Placanja;

import java.util.List;

public interface TransakcijaServis {
    public PrenosSredstava nadjiPrenosSretstavaPoId(Long id);
    public Placanja nadjiUplatuPoId(Long id);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id);
    public PlacanjaDTO dobaciUplatuSretstavaDTOPoID(Long id);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public PlacanjaDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
    public PlacanjaDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    public List<PrenosSredstava> nadjiPrenosSretstavaKojiSuUObradi();
    public List<Placanja> nadjiUplateKojiSuUObradi();
    public String izracunajRezervisaneResurse(Long idRacuna);
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);
}
