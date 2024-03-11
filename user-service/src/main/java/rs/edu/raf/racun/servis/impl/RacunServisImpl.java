package rs.edu.raf.racun.servis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.racun.dto.NoviDevizniRacunDTO;
import rs.edu.raf.racun.dto.NoviPravniRacunDTO;
import rs.edu.raf.racun.dto.NoviTekuciRacunDTO;
import rs.edu.raf.racun.dto.RacunDTO;
import rs.edu.raf.racun.model.DevizniRacun;
import rs.edu.raf.racun.model.PravniRacun;
import rs.edu.raf.racun.model.TekuciRacun;
import rs.edu.raf.racun.repository.ValuteRepository;
import rs.edu.raf.racun.servis.RacunServis;
import rs.edu.raf.transakcija.repository.DevizniRacunRepository;
import rs.edu.raf.transakcija.repository.PravniRacunRepository;
import rs.edu.raf.transakcija.repository.TekuciRacunRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RacunServisImpl implements RacunServis {

    private Map<String, Integer> vrsteRacuna;
    private DevizniRacunRepository devizniRacunRepository;
    private ValuteRepository valuteRepository;
    private PravniRacunRepository pravniRacunRepository;
    private TekuciRacunRepository tekuciRacunRepository;

    @Autowired
    public RacunServisImpl(DevizniRacunRepository devizniRacunRepository, ValuteRepository valuteRepository, PravniRacunRepository pravniRacunRepository, TekuciRacunRepository tekuciRacunRepository) {
        this.devizniRacunRepository = devizniRacunRepository;
        this.valuteRepository = valuteRepository;
        this.pravniRacunRepository = pravniRacunRepository;
        this.tekuciRacunRepository = tekuciRacunRepository;
        initialiseVrste();
    }

    @Override
    public DevizniRacun kreirajDevizniRacun(NoviDevizniRacunDTO noviDevizniRacunDTO) {
        DevizniRacun dr = new DevizniRacun();

        Long id = devizniRacunRepository.findTop1ByOrderByIdDesc() * 10000000000L;
        dr.setBrojRacuna(444000000000000011L + id); //444 sifra banke 0000 filial

        dr.setVlasnik(noviDevizniRacunDTO.getVlasnik());
        dr.setStanje(new BigDecimal("0"));
        dr.setRaspolozivoStanje(new BigDecimal("0"));
        dr.setZaposleni(noviDevizniRacunDTO.getZaposleni());
        dr.setDatumKreiranja(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        dr.setDatumIsteka(dr.getDatumKreiranja() + 5*31536000L);

        List<String> nazivi = noviDevizniRacunDTO.getCurrency();
        String valute = "";
        for (String naziv : nazivi) {
            valute = valute.concat(naziv + ",");
        }
        valute = valute.substring(0, valute.length() - 1);
        dr.setCurrency(valute);

        dr.setDefaultCurrency(noviDevizniRacunDTO.getDefaultCurrency());
        dr.setAktivan(true);
        dr.setKamatnaStopa(new BigDecimal("1"));
        dr.setOdrzavanjeRacuna(new BigDecimal(100 * noviDevizniRacunDTO.getBrojDozvoljenihValuta()));
        dr.setBrojDozvoljenihValuta(noviDevizniRacunDTO.getBrojDozvoljenihValuta());

        return this.devizniRacunRepository.save(dr);
    }

    @Override
    public PravniRacun kreirajPravniRacun(NoviPravniRacunDTO noviPravniRacunDTO) {
        PravniRacun pr = new PravniRacun();

        Long id = pravniRacunRepository.findTop1ByOrderByIdDesc() * 10000000000L;
        pr.setBrojRacuna(333444400000000022L + id); //TODO odluciti se za sifru banke

        pr.setFirma(noviPravniRacunDTO.getFirma());
        pr.setStanje(new BigDecimal("0"));
        pr.setRaspolozivoStanje(new BigDecimal("0"));
        pr.setZaposleni(noviPravniRacunDTO.getZaposleni());
        pr.setDatumKreiranja(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        pr.setDatumIsteka(pr.getDatumKreiranja() + 5*31536000L);
        pr.setCurrency("Srpski dinar");
        pr.setAktivan(true);

        return this.pravniRacunRepository.save(pr);
    }

    @Override
    public TekuciRacun kreirajTekuciRacun(NoviTekuciRacunDTO noviTekuciRacunDTO) {
        TekuciRacun tr = new TekuciRacun();

        Long id = tekuciRacunRepository.findTop1ByOrderByIdDesc() * 10000000000L;
        tr.setBrojRacuna(333444400000000033L + id); //TODO odluciti se za sifru banke

        tr.setVlasnik(noviTekuciRacunDTO.getVlasnik());
        tr.setStanje(new BigDecimal("0"));
        tr.setRaspolozivoStanje(new BigDecimal("0"));
        tr.setZaposleni(noviTekuciRacunDTO.getZaposleni());
        tr.setDatumKreiranja(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        tr.setDatumIsteka(tr.getDatumKreiranja() + 5*31536000L);
        tr.setCurrency("Srpski dinar");
        tr.setAktivan(true);
        tr.setVrstaRacuna(noviTekuciRacunDTO.getVrstaRacuna());

        if (tr.getVrstaRacuna().equals("Penzionerski") || tr.getVrstaRacuna().equals("Studentski")) {
            tr.setKamatnaStopa(new BigDecimal("0.5"));
        } else {
            tr.setKamatnaStopa(new BigDecimal("0"));
        }

        tr.setOdrzavanjeRacuna(new BigDecimal(100 * vrsteRacuna.get(tr.getVrstaRacuna())));

        return this.tekuciRacunRepository.save(tr);
    }

    @Override
    public List<RacunDTO> izlistavanjeRacunaJednogKorisnika(Long idKorisnika) {
        return null;
    }

    @Override
    public RacunDTO nadjiAktivanRacunPoID(Long id) {
        return null;
    }

    @Override
    public DevizniRacun nadjiAktivanDevizniRacunPoID(Long id) {
        return null;
    }

    @Override
    public PravniRacun nadjiAktivanPravniRacunPoID(Long id) {
        return null;
    }

    @Override
    public TekuciRacun nadjiAktivanTekuciRacunPoID(Long id) {
        return null;
    }

    @Override
    public RacunDTO nadjiAktivanRacunPoBrojuRacuna(Long BrojRacuna) {
        return null;
    }

    @Override
    public DevizniRacun nadjiAktivanDevizniRacunPoBrojuRacuna(Long BrojRacuna) {
        return null;
    }

    @Override
    public PravniRacun nadjiAktivanPravniRacunPoBrojuRacuna(Long BrojRacuna) {
        return null;
    }

    @Override
    public TekuciRacun nadjiAktivanTekuciRacunPoBrojuRacuna(Long BrojRacuna) {
        return null;
    }

    @Override
    public boolean dodajDevizniRacunKorisniku(DevizniRacun devizniRacun, Korisnik korisnik) {
        return false;
    }

    @Override
    public boolean dodajPravniRacunKorisniku(PravniRacun pravniRacun, Korisnik korisnik) {
        return false;
    }

    @Override
    public boolean dodajTekuciRacunKorisniku(TekuciRacun tekuciRacun, Korisnik korisnik) {
        return false;
    }

    @Override
    public Long generisiBrojRacuna(String tipRacuna) {
        return null;
    }

    @Override
    public String nadjiVrstuRacuna(Long BrojRacuna) {
        return null;
    }

    private void initialiseVrste() {
        vrsteRacuna = new HashMap<>();
        vrsteRacuna.put("Poslovni", 5);
        vrsteRacuna.put("Lični", 3);
        vrsteRacuna.put("Štedni", 2);
        vrsteRacuna.put("Penzionerski", 2);
        vrsteRacuna.put("Devizni", 5);
        vrsteRacuna.put("Studentski", 0);
    }
}
