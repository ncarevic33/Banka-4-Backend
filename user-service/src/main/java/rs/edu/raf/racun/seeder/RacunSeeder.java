package rs.edu.raf.racun.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.korisnik.model.Radnik;
import rs.edu.raf.racun.model.*;
import rs.edu.raf.racun.repository.FirmaRepository;
import rs.edu.raf.racun.repository.ValuteRepository;
import rs.edu.raf.racun.repository.ZemljaRepository;
import rs.edu.raf.transakcija.repository.DevizniRacunRepository;
import rs.edu.raf.transakcija.repository.PravniRacunRepository;
import rs.edu.raf.transakcija.repository.TekuciRacunRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RacunSeeder implements CommandLineRunner {

    private final ZemljaRepository zemljaRepository;
    private final ValuteRepository valuteRepository;
    private final FirmaRepository firmaRepository;

    private final DevizniRacunRepository devizniRacunRepository;
    private final PravniRacunRepository pravniRacunRepository;

    private final TekuciRacunRepository tekuciRacunRepository;

    @Autowired
    public RacunSeeder(ZemljaRepository zemljaRepository, ValuteRepository valuteRepository, FirmaRepository firmaRepository, DevizniRacunRepository devizniRacunRepository, PravniRacunRepository pravniRacunRepository, TekuciRacunRepository tekuciRacunRepository) {
        this.zemljaRepository = zemljaRepository;
        this.valuteRepository = valuteRepository;
        this.firmaRepository = firmaRepository;
        this.devizniRacunRepository = devizniRacunRepository;
        this.pravniRacunRepository = pravniRacunRepository;
        this.tekuciRacunRepository = tekuciRacunRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Zemlja> zemlje = new ArrayList<>();
        Zemlja z1 = new Zemlja("Švajcarska Konfederacija");
        zemlje.add(z1);
        Zemlja z2 = new Zemlja("Sjedinjene Američke Države");
        zemlje.add(z2);
        Zemlja z3 = new Zemlja("Francuska Republika");
        zemlje.add(z3);
        Zemlja z4 = new Zemlja("Savezna Republika Nemačka");
        zemlje.add(z4);
        Zemlja z5 = new Zemlja("Ujedinjeno Kraljevstvo Velike Britanije i Severne Irske");
        zemlje.add(z5);
        Zemlja z6 = new Zemlja("Japan");
        zemlje.add(z6);
        Zemlja z7 = new Zemlja("Kanada");
        zemlje.add(z7);
        Zemlja z8 = new Zemlja("Komonvelt Australija");
        zemlje.add(z8);
        Zemlja z9 = new Zemlja("Republika Srbija");
        zemlje.add(z9);
        this.zemljaRepository.saveAll(zemlje);

        List<Valute> valute = new ArrayList<>();
        Valute v1 = new Valute("Švajcarski franak", "CHF", "fr.", z1.getNaziv());
        valute.add(v1);
        Valute v2 = new Valute("Američki dolar", "USD", "$", z2.getNaziv());
        valute.add(v2);
        Valute v3 = new Valute("Evro", "EUR", "€", z3.getNaziv() + "," + z4.getNaziv());
        valute.add(v3);
        Valute v4 = new Valute("Britanska funta", "GBP", "£", z5.getNaziv());
        valute.add(v4);
        Valute v5 = new Valute("Japanski jen", "JPY", "¥", z6.getNaziv());
        valute.add(v5);
        Valute v6 = new Valute("Kanadijski dolar", "CAD", "$", z7.getNaziv());
        valute.add(v6);
        Valute v7 = new Valute("Australijski dolar", "AUD", "$", z8.getNaziv());
        valute.add(v7);
        Valute v8 = new Valute("Srpski dinar", "RSD", "дин.", z9.getNaziv());
        valute.add(v8);
        valuteRepository.saveAll(valute);

        List<Firma> firme = new ArrayList<>();
        Firma f1 = new Firma(33333L, "Belit d.o.o. Beograd", "444000000900000022", "0112030403", "0112030402", 101017533, 17328905, 6102, 130501701);
        firme.add(f1);
        Firma f2 = new Firma(44444L, "Factory World Wide", "444000000910000022", "0112030403", "0112030402", 101017533, 17328905, 6102, 130501701);
        firme.add(f2);
        Firma f3 = new Firma(55555L, "EM Analytic Solutions", "444000000920000022", "0112030403", "0112030402", 101017533, 17328905, 6102, 130501701);
        firme.add(f3);
        firmaRepository.saveAll(firme);

        List<DevizniRacun> dRacuni = new ArrayList<>();
        DevizniRacun dr1 = new DevizniRacun(444000000900000011L, 11111L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v1.getNaziv() + "," + v8.getNaziv(), v8.getNaziv(), true, new BigDecimal("1"), new BigDecimal(100 * 2), 2);
        dRacuni.add(dr1);
        DevizniRacun dr2 = new DevizniRacun(444000000910000011L, 11111L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v4.getNaziv() + "," + v8.getNaziv(), v4.getNaziv(), true, new BigDecimal("1"), new BigDecimal(100 * 2), 2);
        dRacuni.add(dr2);
        DevizniRacun dr3 = new DevizniRacun(444000000920000011L, 11111L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v5.getNaziv() + "," + v6.getNaziv() + "," + v8.getNaziv(), v8.getNaziv(), true, new BigDecimal("1"), new BigDecimal(100 * 3), 3);
        dRacuni.add(dr3);
        devizniRacunRepository.saveAll(dRacuni);

        List<PravniRacun> pRacuni = new ArrayList<>();
        PravniRacun pr1 = new PravniRacun(444000000900000022L, 33333L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true);
        pRacuni.add(pr1);
        PravniRacun pr2 = new PravniRacun(444000000910000022L, 44444L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true);
        pRacuni.add(pr2);
        PravniRacun pr3 = new PravniRacun(444000000920000022L, 55555L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true);
        pRacuni.add(pr3);
        pravniRacunRepository.saveAll(pRacuni);

        List<TekuciRacun> tRacuni = new ArrayList<>();
        TekuciRacun tr1 = new TekuciRacun(444000000900000033L, 1L, new BigDecimal("1000"), new BigDecimal("1000"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true, "Studentski", new BigDecimal("0.5"), new BigDecimal("0"));
        tRacuni.add(tr1);
        TekuciRacun tr2 = new TekuciRacun(444000000910000033L, 11111L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true, "Licni", new BigDecimal("0"), new BigDecimal("300"));
        tRacuni.add(tr2);
        TekuciRacun tr3 = new TekuciRacun(444000000920000033L, 11111L, new BigDecimal("0"), new BigDecimal("0"), 22222L, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 5*31536000L, v8.getNaziv(), true, "Stedni", new BigDecimal("0"), new BigDecimal("200"));
        tRacuni.add(tr3);
        tekuciRacunRepository.saveAll(tRacuni);
    }
}
