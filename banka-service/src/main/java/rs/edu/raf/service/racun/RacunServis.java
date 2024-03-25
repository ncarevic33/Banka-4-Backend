package rs.edu.raf.service.racun;

import rs.edu.raf.model.dto.racun.*;
import rs.edu.raf.model.entities.racun.DevizniRacun;
import rs.edu.raf.model.entities.racun.Firma;
import rs.edu.raf.model.entities.racun.PravniRacun;
import rs.edu.raf.model.entities.racun.TekuciRacun;

import java.util.List;
public interface RacunServis {
    public DevizniRacun kreirajDevizniRacun(NoviDevizniRacunDTO noviDevizniRacunDTO);
    public PravniRacun kreirajPravniRacun(NoviPravniRacunDTO noviPravniRacunDTO);
    public TekuciRacun kreirajTekuciRacun(NoviTekuciRacunDTO noviTekuciRacunDTO);
    public List<RacunDTO> izlistavanjeRacunaJednogKorisnika(Long idKorisnika);
    public RacunDTO nadjiAktivanRacunPoID(Long id);
    public DevizniRacun nadjiAktivanDevizniRacunPoID(Long id);
    public PravniRacun nadjiAktivanPravniRacunPoID(Long id);
    public TekuciRacun nadjiAktivanTekuciRacunPoID(Long id);
    public RacunDTO nadjiAktivanRacunPoBrojuRacuna(Long BrojRacuna);
    public DevizniRacun nadjiAktivanDevizniRacunPoBrojuRacuna(Long BrojRacuna);
    public PravniRacun nadjiAktivanPravniRacunPoBrojuRacuna(Long BrojRacuna);
    public TekuciRacun nadjiAktivanTekuciRacunPoBrojuRacuna(Long BrojRacuna);

    public boolean dodajPravniRacunFirmi(PravniRacun pravniRacun, Firma firma);

    public Long generisiBrojRacuna(String tipRacuna);
    public String nadjiVrstuRacuna(Long BrojRacuna);
    public List<FirmaDTO> izlistajSveFirme();
    public Firma kreirajFirmu(NovaFirmaDTO firma);
    public boolean deaktiviraj(Long brojRacuna);

    public Long nadjiKorisnikaPoBrojuRacuna(Long brojRacuna);
    public Object nadjiRacunPoBrojuRacuna(Long brojRacuna);
}
