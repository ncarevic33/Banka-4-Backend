package rs.edu.raf.racun.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.edu.raf.racun.model.Firma;
import rs.edu.raf.racun.model.Valute;
import rs.edu.raf.racun.model.Zemlja;
import rs.edu.raf.racun.repository.FirmaRepository;
import rs.edu.raf.racun.repository.ValuteRepository;
import rs.edu.raf.racun.repository.ZemljaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RacunSeeder implements CommandLineRunner {

    private final ZemljaRepository zemljaRepository;
    private final ValuteRepository valuteRepository;
    private final FirmaRepository firmaRepository;

    @Autowired
    public RacunSeeder(ZemljaRepository zemljaRepository, ValuteRepository valuteRepository, FirmaRepository firmaRepository) {
        this.zemljaRepository = zemljaRepository;
        this.valuteRepository = valuteRepository;
        this.firmaRepository = firmaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Zemlja> zemlje = new ArrayList<>();
        Zemlja z1 = new Zemlja();
        z1.setNaziv("Švajcarska Konfederacija");
        zemlje.add(z1);
        Zemlja z2 = new Zemlja();
        z2.setNaziv("Sjedinjene Američke Države");
        zemlje.add(z2);
        Zemlja z3 = new Zemlja();
        z3.setNaziv("Francuska Republika");
        zemlje.add(z3);
        Zemlja z4 = new Zemlja();
        z4.setNaziv("Savezna Republika Nemačka");
        zemlje.add(z4);
        Zemlja z5 = new Zemlja();
        z5.setNaziv("Ujedinjeno Kraljevstvo Velike Britanije i Severne Irske");
        zemlje.add(z5);
        Zemlja z6 = new Zemlja();
        z6.setNaziv("Japan");
        zemlje.add(z6);
        Zemlja z7 = new Zemlja();
        z7.setNaziv("Kanada");
        zemlje.add(z7);
        Zemlja z8 = new Zemlja();
        z8.setNaziv("Komonvelt Australija");
        zemlje.add(z8);
        Zemlja z9 = new Zemlja();
        z9.setNaziv("Republika Srbija");
        zemlje.add(z9);
        this.zemljaRepository.saveAll(zemlje);

        List<Valute> valute = new ArrayList<>();
        Valute v1 = new Valute();
        v1.setNaziv("Švajcarski franak");
        v1.setOznaka("CHF");
        v1.setSimbol("fr.");
        v1.setZemlje(z1.getNaziv());
        valute.add(v1);
        Valute v2 = new Valute();
        v2.setNaziv("Američki dolar");
        v2.setOznaka("USD");
        v2.setSimbol("$");
        v2.setZemlje(z2.getNaziv());
        valute.add(v2);
        Valute v3 = new Valute();
        v3.setNaziv("Evro");
        v3.setOznaka("EUR");
        v3.setSimbol("€");
        v3.setZemlje(z3.getNaziv() + "," + z4.getNaziv());
        valute.add(v3);
        Valute v4 = new Valute();
        v4.setNaziv("Britanska funta");
        v4.setOznaka("GBP");
        v4.setSimbol("£");
        v4.setZemlje(z5.getNaziv());
        valute.add(v4);
        Valute v5 = new Valute();
        v5.setNaziv("Japanski jen");
        v5.setOznaka("JPY");
        v5.setSimbol("¥");
        v5.setZemlje(z6.getNaziv());
        valute.add(v5);
        Valute v6 = new Valute();
        v6.setNaziv("Kanadijski dolar");
        v6.setOznaka("CAD");
        v6.setSimbol("$");
        v6.setZemlje(z7.getNaziv());
        valute.add(v6);
        Valute v7 = new Valute();
        v7.setNaziv("Australijski dolar");
        v7.setOznaka("AUD");
        v7.setSimbol("$");
        v7.setZemlje(z8.getNaziv());
        valute.add(v7);
        Valute v8 = new Valute();
        v8.setNaziv("Srpski dinar");
        v8.setOznaka("RSD");
        v8.setSimbol("дин.");
        v8.setZemlje(z9.getNaziv());
        valute.add(v8);
        valuteRepository.saveAll(valute);

        List<Firma> firme = new ArrayList<>();
        Firma f1 = new Firma();
        f1.setNazivPreduzeca("Belit d.o.o. Beograd");
        f1.setBrojTelefona("0112030403");
        f1.setBrojFaksa("0112030402");
        f1.setPIB(101017533);
        f1.setMaticniBroj(17328905);
        f1.setSifraDelatnosti(6102);
        f1.setRegistarskiBroj(130501701);
        firme.add(f1);
        firmaRepository.saveAll(firme);
    }
}
