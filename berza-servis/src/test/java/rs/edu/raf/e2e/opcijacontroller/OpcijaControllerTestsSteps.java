package rs.edu.raf.e2e.opcijacontroller;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.awaitility.Awaitility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import rs.edu.raf.opcija.controller.OpcijaController;
import rs.edu.raf.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.opcija.dto.OpcijaDto;
import rs.edu.raf.opcija.model.KorisnikoveKupljeneOpcije;
import rs.edu.raf.opcija.model.OpcijaStanje;
import rs.edu.raf.opcija.model.OpcijaTip;
import rs.edu.raf.opcija.repository.KorisnikRepository;
import rs.edu.raf.opcija.repository.OpcijaRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;





public class OpcijaControllerTestsSteps extends OpcijaControllerTestsConfig{


    //mora se zaobici autentifikacija i autorizacija ili se ulogovati u @Given koji svi scenariji imaju
    //@Autowired
    //private MockMvc mockMvc;

    //@Autowired
    //private ObjectMapper objectMapper;

    //private MvcResult mvcResult;


    @Autowired//moramo proveriti da li su podaci dostupni u bazi pre testa
    private OpcijaRepository opcijaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    //ruta zahteva autorizaciju pa joj ne pristupamo preko mockMvc
    private ResponseEntity<List<OpcijaDto>> dobijeneSveOpcije;
    private ResponseEntity<List<OpcijaDto>> dobijeneOpcijePoTickeru;
    private ResponseEntity<List<OpcijaDto>> dobijeneOpcijePoDatumIsteka;
    private ResponseEntity<List<OpcijaDto>> dobijeneOpcijePoStrikePrice;
    private ResponseEntity<List<OpcijaDto>> dobijeneOpcijePoTikceruIDatumIstekaIStrikePrice;

    private ResponseEntity<OpcijaStanje> opcijaStanje;
    private ResponseEntity<OpcijaDto> kreiranaOpcija;
    private ResponseEntity<KorisnikoveKupljeneOpcije> korisnikoveKupljeneOpcijeResponseEntity;

    @Autowired
    private OpcijaController opcijaController;

    @Given("api poziv za opcije je prethodno izvrsen")
    public void apiPozivZaOpcijeJePrethodnoIzvrsen() {

                                    //ceka najvise zadatih sekudni dok se ne pojave podaci u opcijaRepository inace failuje
        Awaitility.await().atMost(10, SECONDS).until(this::checkIfOpcijeAreLoaded);
    }
    private boolean checkIfOpcijeAreLoaded() {
        return !opcijaRepository.findAll().isEmpty();
    }

    @When("gadjamo endpoint za dohvatanje svih opcija")
    public void gadjamoEndpointZaDohvatanjeSvihOpcija() {

        try {
            dobijeneSveOpcije = opcijaController.findAll();
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @When("gadjamo endpoint za filtriranje opcija sa tickerom {string}")
    public void gadjamoEndpointZaFiltriranjeOpcijaSaTickerom(String arg0) {

        dobijeneOpcijePoTickeru = opcijaController.filtrirajOpcije(arg0,null,null);

    }


    @When("gadjamo endpoint za filtriranje opcija sa datumIsteka {string}")
    public void gadjamoEndpointZaFiltriranjeOpcijaSaDatumIsteka(String arg0) {
        /*try {
            ResultActions resultActions = mockMvc.perform(get("/api/opcija/filtrirajOpcije?datumIsteka="+arg0))
                    .andExpect(status().isOk());

            mvcResult = resultActions.andReturn();
        } catch (Exception e) {
            fail(e.getMessage());
        }*/
        dobijeneOpcijePoDatumIsteka = opcijaController.filtrirajOpcije(null,Long.valueOf(arg0),null);

    }


    @When("gadjamo endpoint za filtriranje opcija sa strikePrice {string}")
    public void gadjamoEndpointZaFiltriranjeOpcijaSaStrikePrice(String arg0) {

        dobijeneOpcijePoStrikePrice = opcijaController.filtrirajOpcije(null,null,Double.valueOf(arg0));

    }


    @When("gadjamo endpoint za filtriranje opcija sa tickerom {string} i datumIsteka {string} i strikePrice {string}")
    public void gadjamoEndpointZaFiltriranjeOpcijaSaTickeromIDatumIstekaIStrikePrice(String arg0, String arg1, String arg2) {

        dobijeneOpcijePoTikceruIDatumIstekaIStrikePrice = opcijaController.filtrirajOpcije(arg0,Long.valueOf(arg1),Double.valueOf(arg2));

    }


    @When("gadjamo endpoint da proverimo stanje opcije sa id {string}")
    public void gadjamoEndpointDaProverimoStanjeOpcijeSaId(String arg0) {

        opcijaStanje = opcijaController.stanjeOpcije(Long.valueOf(arg0));


    }

    @Then("dobijemo stanje opcije")
    public void dobijemoStanjeOpcije() {

        assertEquals(200, opcijaStanje.getStatusCodeValue());

    }

    @Given("api poziv za opcije je prethodno izvrsen i korisnik sa id {string} postoji u bazi")
    public void apiPozivZaOpcijeJePrethodnoIzvrsenIKorisnikSaIdPostojiUBazi(String arg0) {
            Awaitility.await().atMost(10, SECONDS).until(this::checkIfOpcijeAreLoaded);

            if(!korisnikRepository.findById(Long.valueOf(arg0)).isPresent())
                fail("Ne postoji korisnik sa zadatim id");
    }

    @When("gadjamo endpoint da izvrsimo opciju sa id {string} i userId {string}")
    public void gadjamoEndpointDaIzvrsimoOpcijuSaIdIUserId(String arg0, String arg1) {


        korisnikoveKupljeneOpcijeResponseEntity = opcijaController.izvrsiKorisnikovuOpciju(Long.valueOf(arg0),Long.valueOf(arg1));

    }

    @Then("opcija je izvrsena")
    public void opcijaJeIzvrsena() {


        assertEquals(200, korisnikoveKupljeneOpcijeResponseEntity.getStatusCodeValue());

    }

    @Then("dobijemo sve opcije")
    public void dobijemoSveOpcije() {

        assertEquals(200, dobijeneSveOpcije.getStatusCodeValue());
        if(dobijeneSveOpcije.getBody().size() == 0)
            fail("Nema opcija");

    }

    @Then("dobijemo opcije sa tickerom {string}")
    public void dobijemoOpcijeSaTickerom(String arg0) {

        assertEquals(200, dobijeneOpcijePoTickeru.getStatusCodeValue());
        if(dobijeneOpcijePoTickeru.getBody().size() == 0)
            fail("Nema opcija");

        for(OpcijaDto o:dobijeneOpcijePoTickeru.getBody()){
            if(!o.getTicker().equals(arg0))
                fail("Pogresan ticker");
        }

    }

    @Then("dobijemo opcije sa datumIsteka {string}")
    public void dobijemoOpcijeSaDatumIsteka(String arg0) {

        assertEquals(200, dobijeneOpcijePoDatumIsteka.getStatusCodeValue());
        if(dobijeneOpcijePoDatumIsteka.getBody().size() == 0)
            fail("Nema opcija");

        for(OpcijaDto o:dobijeneOpcijePoDatumIsteka.getBody()){
            if(o.getExpiration()!=Long.valueOf(arg0))
                fail("Pogresan datum isteka");
        }
    }

    @Then("dobijemo opcije sa strikePrice {string}")
    public void dobijemoOpcijeSaStrikePrice(String arg0) {

        assertEquals(200, dobijeneOpcijePoStrikePrice.getStatusCodeValue());
        if(dobijeneOpcijePoStrikePrice.getBody().size() == 0)
            fail("Nema opcija");

        for(OpcijaDto o:dobijeneOpcijePoStrikePrice.getBody()){
            if(o.getStrikePrice()!=Double.valueOf(arg0))
                fail("Pogresan strike price");
        }

    }

    @Then("dobijemo opcije sa tickerom {string} i datumIsteka {string} i strikePrice {string}")
    public void dobijemoOpcijeSaTickeromIDatumIstekaIStrikePrice(String arg0, String arg1, String arg2) {

        assertEquals(200, dobijeneOpcijePoTikceruIDatumIstekaIStrikePrice.getStatusCodeValue());
        if(dobijeneOpcijePoTikceruIDatumIstekaIStrikePrice.getBody().size() == 0)
            fail("Nema opcija");

        for(OpcijaDto o:dobijeneOpcijePoTikceruIDatumIstekaIStrikePrice.getBody()){
            if(!o.getTicker().equals(arg0) || o.getExpiration()!=Long.valueOf(arg1) || o.getStrikePrice()!=Double.valueOf(arg2))
                fail("Pogresan ticker ili datum isteka ili strike price");
        }


    }

    @When("gadjamo endpoint za kreiranje opcije")
    public void gadjamoEndpointZaKreiranjeOpcije() {
        NovaOpcijaDto novaOpcijaDto = new NovaOpcijaDto();
        novaOpcijaDto.setContractSymbol("ABC");
        novaOpcijaDto.setTicker("AAPL");
        novaOpcijaDto.setOptionType(OpcijaTip.PUT);
        novaOpcijaDto.setOpenInterest(1000);
        novaOpcijaDto.setImpliedVolatility(1000);
        novaOpcijaDto.setBrojUgovora(1000);
        novaOpcijaDto.setSettlementDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(10000), ZoneOffset.systemDefault()));
        kreiranaOpcija = opcijaController.kreirajOpciju(novaOpcijaDto);
    }

    @Then("dobijemo kreiranu opciju")
    public void dobijemoKreiranuOpciju() {
        assertEquals(200, kreiranaOpcija.getStatusCodeValue());
        if(kreiranaOpcija.getBody() == null)
            fail("Nema opcije");

    }
}
