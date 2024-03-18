package rs.edu.raf.berza.opcija.servis.impl;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.mapper.OpcijaMapper;
import rs.edu.raf.berza.opcija.model.Opcija;
import rs.edu.raf.berza.opcija.repository.OpcijaRepository;
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
    private OpcijaMapper opcijaMapper;

    @Autowired
    private OpcijaRepository opcijaRepository;

    @PostConstruct
    public void init() throws IOException {
        Thread backgroundFirstTimeFetching = new Thread(() -> {
            try {
                opcijaRepository.saveAll(fetchAllOptionsForAllTickers());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        backgroundFirstTimeFetching.start();
    }

    private List<Opcija> fetchAllOptionsForAllTickers() throws IOException {
        List<String> tickerNames = FinansijaApiUtil.fetchTickerNames();

        if(tickerNames.size() == 0)
            return new ArrayList<>();

        //opcijaRepository.saveAll(FinansijaApiUtil.fetchOptionsFromYahooApi(tickerNames));
        List<OptionYahooApiMap> opcije = FinansijaApiUtil.fetchOptionsFromYahooApi(tickerNames);

        log.info("gotovo");
        return opcije.stream().map(yahooOpcija -> opcijaMapper.yahooOpcijaToOpcija(yahooOpcija)).collect(Collectors.toList());
    }

    @Override
    public List<OpcijaDto> findAll() throws InterruptedException {


        List<Opcija> opcije = opcijaRepository.findAll();
        /*List<OpcijaDto> opcijaDtos = new ArrayList<>();
        for(Opcija o:opcije){
            opcijaDtos.add(opcijaMapper.opcijaToOpcijaDto(o));
        }*/

        return opcije.stream().map(opcija -> opcijaMapper.opcijaToOpcijaDto(opcija)).collect(Collectors.toList());

    }

    @Override
    public List<OpcijaDto> findByStockAndDateAndStrike(String ticker, LocalDateTime datumIstekaVazenja, double strikePrice) {
        List<Opcija> opcije = this.opcijaRepository.findByStockAndDateAndStrike(ticker,datumIstekaVazenja,strikePrice);//(page-1)*6
        return opcije.stream().map(opcija -> opcijaMapper.opcijaToOpcijaDto(opcija)).collect(Collectors.toList());
    }

    @Override
    public boolean izvrsiOpciju(Long opcijaId) {
        Optional<Opcija> opcija = opcijaRepository.findById(opcijaId);

        return false;
    }
    public OpcijaDto proveriStanjeOpcije(Long opcijaId){
        return null;
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


    @Override
    @Scheduled(fixedRate = 9000)
    public void azuirajPostojeceOpcije() throws IOException {
        List<Opcija> noveAzuriraneOpcije = fetchAllOptionsForAllTickers();
        List<Opcija> postojeceOpcije = opcijaRepository.findAll();

        for(Opcija o:noveAzuriraneOpcije){
            Optional<Opcija> postojecaOpcija = postojeceOpcije.stream()
                    .filter(opcija -> opcija.getContractSymbol().equals(o.getContractSymbol()))
                    .findFirst();

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

                postojeca.izracunajIzvedeneVrednosti();

            });
        }

    }


}
