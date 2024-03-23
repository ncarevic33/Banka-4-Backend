package rs.edu.raf.berza.opcija.servis.impl;

import io.cucumber.java.bs.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.mapper.OpcijaMapper;
import rs.edu.raf.berza.opcija.model.*;
import rs.edu.raf.berza.opcija.repository.*;
import rs.edu.raf.berza.opcija.servis.IzvedeneVrednostiUtil;
import rs.edu.raf.berza.opcija.servis.OpcijaServis;
import rs.edu.raf.berza.opcija.servis.util.FinansijaApiUtil;
import rs.edu.raf.berza.opcija.servis.util.OptionYahooApiMap;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//direktno komunicira sa bazom i sluzi za operacije nad entitetom pre upisa u bazu
@Service
public class OpcijaServisImpl implements OpcijaServis {

    private final static Logger log = LoggerFactory.getLogger(OpcijaServisImpl.class.getSimpleName());

    @Autowired
    private IzvedeneVrednostiUtil izvedeneVrednostiUtil;

    @Autowired
    private FinansijaApiUtil finansijaApiUtil;

    @Autowired
    private KorisnikAkcijaRepository korisnikAkcijaRepository;

    @Autowired
    private KorisnikoveOpcijeRepository korisnikOpcijaRepository;

    @Autowired
    private AkcijaRepository akcijaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private OpcijaMapper opcijaMapper;

    @Autowired
    private OpcijaRepository opcijaRepository;


    private List<Opcija> fetchAllOptionsForAllTickers() throws IOException {
        List<String> tickerNames = finansijaApiUtil.fetchTickerNames();

        if(tickerNames.size() == 0)
            return new ArrayList<>();
                                                                                                                //staviti na tickerNames
        List<OptionYahooApiMap> yahooOpcije = finansijaApiUtil.fetchOptionsFromYahooApi(Collections.singletonList(tickerNames.get(0)));

        //log.info(String.valueOf(System.currentTimeMillis()));
        log.info("Gotovo fetchovanje sa yahoo api");
        return yahooOpcije.stream().map(yahooOpcija -> opcijaMapper.yahooOpcijaToOpcija(yahooOpcija)).collect(Collectors.toList());
    }

    @Override
    public List<OpcijaDto> findAll()  {

        List<Opcija> opcije = opcijaRepository.findAll();

        return opcije.stream().map(opcija -> opcijaMapper.opcijaToOpcijaDto(opcija)).collect(Collectors.toList());

    }

    @Override
    public List<OpcijaDto> findByStockAndDateAndStrike(String ticker, LocalDateTime datumIstekaVazenja, double strikePrice) {
        List<Opcija> opcije = this.opcijaRepository.findByStockAndDateAndStrike(ticker,datumIstekaVazenja,strikePrice);//(page-1)*6
        return opcije.stream().map(opcija -> opcijaMapper.opcijaToOpcijaDto(opcija)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean izvrsiOpciju(Long opcijaId,Long userId) {
                                                            //moze ih biti vise pa uzimamo prvu
        KorisnikoveOpcije korisnikKupljenaOpcija = korisnikOpcijaRepository.findFirstByOpcijaIdAndKorisnikId(opcijaId, userId).orElse(null);
        Opcija opcija = opcijaRepository.findById(opcijaId).orElse(null);
        Korisnik korisnik = korisnikRepository.findById(userId).orElse(null);

        if(korisnik == null || korisnikKupljenaOpcija == null || opcija == null || opcija.getOpcijaStanje().equals(OpcijaStanje.EXPIRED))//nema ni jednu konkretnu kupljenu opciju
            return false;


        Akcija akcija = akcijaRepository.findFirstByTicker(opcija.getTicker());


        KorisnikKupljeneAkcije korisnikKupljeneAkcije = new KorisnikKupljeneAkcije();
        korisnikKupljeneAkcije.setKorisnikId(korisnik.getId());
        korisnikKupljeneAkcije.setContractSize(opcija.getContractSize());
        korisnikKupljeneAkcije.setStrikePrice(opcija.getStrikePrice());
        korisnikKupljeneAkcije.setAkcijaTickerTrenutnaCena(akcija.getAkcijaTickerTrenutnaCena());
        korisnikKupljeneAkcije.setOpcijaTip(opcija.getOptionType().equals(OpcijaTip.PUT)?OpcijaTip.PUT:OpcijaTip.CALL);
        korisnikAkcijaRepository.save(korisnikKupljeneAkcije);

                                    //ako korisnik ima vise istih opcija brisemo prvu bilo koju
        korisnikOpcijaRepository.deleteFirstByOpcijaIdAndKorisnikId(opcijaId,userId);

        return true;
    }


    @Override
    public OpcijaStanje proveriStanjeOpcije(Long opcijaId){

        Opcija opcija = opcijaRepository.findById(opcijaId).get();


        if (opcija.getOpcijaStanje().equals(OpcijaStanje.EXPIRED))
            return OpcijaStanje.EXPIRED;

        Akcija akcija = akcijaRepository.findFirstByTicker(opcija.getTicker());
        OpcijaStanje opcijaStanje = null;

        if(opcija.getStrikePrice() > akcija.getAkcijaTickerTrenutnaCena() && opcija.getOptionType().equals(OpcijaTip.PUT))
            opcijaStanje = OpcijaStanje.IN_THE_MONEY;
        else if(opcija.getStrikePrice() < akcija.getAkcijaTickerTrenutnaCena() && opcija.getOptionType().equals(OpcijaTip.PUT))
            opcijaStanje = OpcijaStanje.OUT_OF_MONEY;

        if(opcija.getStrikePrice() < akcija.getAkcijaTickerTrenutnaCena() && opcija.getOptionType().equals(OpcijaTip.CALL))
            opcijaStanje = OpcijaStanje.IN_THE_MONEY;
        else if(opcija.getStrikePrice() > akcija.getAkcijaTickerTrenutnaCena() && opcija.getOptionType().equals(OpcijaTip.CALL))
            opcijaStanje = OpcijaStanje.OUT_OF_MONEY;

        if(opcijaStanje == null)
            return OpcijaStanje.AT_THE_MONEY;

        return opcijaStanje;
    }
    @Override
    public OpcijaDto save(NovaOpcijaDto novaOpcijaDto) {

        Opcija opcija = opcijaMapper.novaOpcijaDtoToOpcija(novaOpcijaDto);
        opcija = opcijaRepository.save(opcija);
        return opcijaMapper.opcijaToOpcijaDto(opcija);
    }

    @Override
    //optional je ili objekat ili null(ako ne postoji u bazi)
    public Optional<Opcija> findById(Long id) {
        return opcijaRepository.findById(id);
    }




    @Cacheable(value = "opcijeCache", key = "'opcijeCache'")
    @CacheEvict(value = "opcijeCache", allEntries = true)//azurira kes metoda sama sebi
    @Override
    //poseban thread obradjuje
    @Scheduled(fixedRate = 10000)
    @Transactional//sve promene nad bazom se upisuju u bazu tek kada se metoda uspesno zavrsi,ako dodje do izuzetka radi se roll back
    public void azuirajPostojeceOpcije() throws IOException {

        List<Opcija> noveAzuriraneOpcije = fetchAllOptionsForAllTickers();
        List<Opcija> postojeceOpcije = opcijaRepository.findAll();
        log.info(String.valueOf(noveAzuriraneOpcije));

        for(Opcija o:noveAzuriraneOpcije){
            Optional<Opcija> postojecaOpcija = postojeceOpcije.stream()
                    .filter(opcija -> opcija.getContractSymbol().equals(o.getContractSymbol()))
                    .findFirst();

            //moze i brisanje svih opcija pa ponovno dodavanje
            /////////////////////////////////////////////////////
            //azuriramo postojece opcije
            postojecaOpcija.ifPresent(postojeca ->{
                postojeca.setStrikePrice(o.getStrikePrice());
                postojeca.setLastPrice(o.getLastPrice());
                postojeca.setBid(o.getBid());
                postojeca.setContractSymbol(o.getContractSymbol());
                postojeca.setOptionType(o.getOptionType());
                postojeca.setAsk(o.getAsk());
                postojeca.setTicker(o.getTicker());
                postojeca.setChange(o.getChange());
                postojeca.setPercentChange(o.getPercentChange());
                postojeca.setInTheMoney(o.isInTheMoney());
                postojeca.setImpliedVolatility(o.getImpliedVolatility());
                postojeca.setOpenInterest(o.getOpenInterest());
                postojeca.setContractSize(o.getContractSize());
                postojeca.setExpiration(o.getExpiration());

                postojeca.setDatumIstekaVazenja(o.getDatumIstekaVazenja());

                LocalDateTime trenutnoVreme = LocalDateTime.now();

                if(o.getDatumIstekaVazenja().isBefore(trenutnoVreme)) {
                    postojeca.setOpcijaStanje(OpcijaStanje.EXPIRED);
                    //postojeca.setIstaIstorijaGroupId();
                }
                postojeca.izracunajIzvedeneVrednosti(izvedeneVrednostiUtil);

                opcijaRepository.save(postojecaOpcija.get());
            });
            if(!postojecaOpcija.isPresent())
                opcijaRepository.save(o);

        }

    }


}
