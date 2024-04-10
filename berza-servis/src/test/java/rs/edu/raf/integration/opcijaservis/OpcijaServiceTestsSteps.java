package rs.edu.raf.integration.opcijaservis;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.awaitility.Awaitility;
import org.springframework.beans.factory.annotation.Autowired;
import rs.edu.raf.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.opcija.dto.OpcijaDto;
import rs.edu.raf.opcija.model.KorisnikoveKupljeneOpcije;
import rs.edu.raf.opcija.model.OpcijaStanje;
import rs.edu.raf.opcija.model.OpcijaTip;
import rs.edu.raf.opcija.repository.OpcijaRepository;
import rs.edu.raf.opcija.servis.OpcijaServis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.fail;

public class OpcijaServiceTestsSteps extends OpcijaServiceTestsConfig{


    @Autowired
    OpcijaServis opcijaServis;

    List<OpcijaDto> sveOpcijeList;
    List<OpcijaDto> opcijePoTicker;
    List<OpcijaDto> opcijePoDatumIsteka;
    List<OpcijaDto> opcijePoStrikePrice;
    OpcijaStanje opcijaStanje;
    KorisnikoveKupljeneOpcije korisnikoveKupljeneOpcije;
    OpcijaDto sacuvanaOpcija;

    @Autowired
    OpcijaRepository opcijaRepository;

    @Given("api poziv za opcije je prethodno izvrsenn")
    public void apiPozivZaOpcijeJePrethodnoIzvrsenn() {

        //ceka najvise zadatih sekudni dok se ne pojave podaci u opcijaRepository inace failuje
        Awaitility.await().atMost(10, SECONDS).until(this::checkIfOpcijeAreLoaded);
    }
    private boolean checkIfOpcijeAreLoaded() {
        return !opcijaRepository.findAll().isEmpty();
    }


    @When("zahtevamo sve opcije")
    public void zahtevamoSveOpcije() {
        try {
            sveOpcijeList = opcijaServis.findAll();
            if(sveOpcijeList.size() == 0)
                fail("Ne postoje opcije");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @When("zahtevamo opciju sa tickerom {string}")
    public void zahtevamoOpcijuSaTickerom(String arg0) {
        opcijePoTicker = opcijaServis.findByStockAndDateAndStrike(arg0,null,null);
    }

    @Then("vraca se opcija sa tickerom {string}")
    public void vracaSeOpcijaSaTickerom(String arg0) {

        if(opcijePoTicker.size() == 0)
            fail("Ne postoje opcije po tickeru");
    }

    @When("zahtevamo opciju sa datumom isteka {string}")
    public void zahtevamoOpcijuSaDatumomIsteka(String arg0) {
        opcijePoDatumIsteka = opcijaServis.findByStockAndDateAndStrike(null, LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(arg0)), ZoneOffset.systemDefault()),null);
    }

    @Then("vraca se opcija sa datumom isteka {string}")
    public void vracaSeOpcijaSaDatumomIsteka(String arg0) {
        if(opcijePoDatumIsteka.size() == 0)
            fail("Ne postoje opcije po datumu isteka");
    }


    @When("zahtevamo opciju sa strike price {string}")
    public void zahtevamoOpcijuSaStrikePrice(String arg0) {
        opcijePoStrikePrice = opcijaServis.findByStockAndDateAndStrike(null, null,Double.valueOf(arg0));
    }

    @Then("vraca se opcija sa strike price {string}")
    public void vracaSeOpcijaSaStrikePrice(String arg0) {
        if(opcijePoDatumIsteka.size() == 0)
            fail("Ne postoje opcije po strike price");
    }

    @When("proveravamo stanje opcije sa id {string}")
    public void proveravamoStanjeOpcijeSaId(String arg0) {
        opcijaStanje = opcijaServis.proveriStanjeOpcije(Long.valueOf(arg0));
    }

    @Then("dobijamo stanje opcije")
    public void dobijamoStanjeOpcije() {
        if(opcijaStanje == null)
            fail("Ne postoji stanje opcije");
    }

    @When("izvrsavamo opciju sa id {string} i userId {string}")
    public void izvrsavamoOpcijuSaIdIUserId(String arg0, String arg1) {
        korisnikoveKupljeneOpcije = opcijaServis.izvrsiOpciju(Long.valueOf(arg0),Long.valueOf(arg1));
    }

    @And("dodamo opciju sa contractSymbol {string}")
    public void dodamoOpcijuSaContractSymbol(String arg0) {
        NovaOpcijaDto novaOpcijaDto = new NovaOpcijaDto();
        novaOpcijaDto.setContractSymbol("ABC");
        novaOpcijaDto.setTicker("AAPL");
        novaOpcijaDto.setOptionType(OpcijaTip.PUT);
        novaOpcijaDto.setOpenInterest(1000);
        novaOpcijaDto.setImpliedVolatility(1000);
        novaOpcijaDto.setBrojUgovora(1000);
        novaOpcijaDto.setSettlementDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(10000), ZoneOffset.systemDefault()));

        sacuvanaOpcija = opcijaServis.save(novaOpcijaDto);

    }

    @Then("vraca se opcija koja ima contractSymbol {string}")
    public void vracaSeOpcijaKojaImaContractSymbol(String arg0) {
        if(sacuvanaOpcija == null)
            fail("Ne postoji opcija sa tim contract symbol");
    }

    @Then("opcija je izvrsenaa")
    public void opcijaJeIzvrsenaa() {
        if(korisnikoveKupljeneOpcije == null)
            fail("Opcija nije izvrsena");
    }

}
