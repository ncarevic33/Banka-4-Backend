package rs.edu.raf.racun.servis;

import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.racun.dto.*;
import rs.edu.raf.racun.model.DevizniRacun;
import rs.edu.raf.racun.model.Firma;
import rs.edu.raf.racun.model.PravniRacun;
import rs.edu.raf.racun.model.TekuciRacun;

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
    public boolean dodajDevizniRacunKorisniku(DevizniRacun devizniRacun, Korisnik korisnik);
    public boolean dodajPravniRacunFirmi(PravniRacun pravniRacun, Firma firma);
    public boolean dodajTekuciRacunKorisniku(TekuciRacun tekuciRacun,Korisnik korisnik);
    public Long generisiBrojRacuna(String tipRacuna);
    public String nadjiVrstuRacuna(Long BrojRacuna);
    public List<FirmaDTO> izlistajSveFirme();
    public Firma kreirajFirmu(NovaFirmaDTO firma);
    public boolean deaktiviraj(Long brojRacuna);
}
