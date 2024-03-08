package rs.edu.raf.racun.servis;

import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.racun.dto.NoviDevizniRacunDTO;
import rs.edu.raf.racun.dto.NoviPravniRacunDTO;
import rs.edu.raf.racun.dto.NoviTekuciRacunDTO;
import rs.edu.raf.racun.dto.RacunDTO;
import rs.edu.raf.racun.model.DevizniRacun;
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
    public boolean dodajPravniRacunKorisniku(PravniRacun pravniRacun,Korisnik korisnik);
    public boolean dodajTekuciRacunKorisniku(TekuciRacun tekuciRacun,Korisnik korisnik);
    public Long generisiBrojRacuna(String tipRacuna);
}
