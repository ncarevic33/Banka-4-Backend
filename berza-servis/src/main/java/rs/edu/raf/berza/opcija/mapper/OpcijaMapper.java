package rs.edu.raf.berza.opcija.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.model.Opcija;
import rs.edu.raf.berza.opcija.model.OpcijaStanje;
import rs.edu.raf.berza.opcija.model.OpcijaTip;
import rs.edu.raf.berza.opcija.servis.IzvedeneVrednostiUtil;
import rs.edu.raf.berza.opcija.servis.util.OptionYahooApiMap;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class OpcijaMapper {

    @Autowired
    private IzvedeneVrednostiUtil izvedeneVrednostiUtil;

    //OD FRONTA
    public Opcija novaOpcijaDtoToOpcija(NovaOpcijaDto novaOpcijaDto){

        Opcija opcija = new Opcija();


        return opcija;
    }
    //KA FRONTU
    public OpcijaDto opcijaToOpcijaDto(Opcija opcija){

        OpcijaDto opcijaDto = new OpcijaDto();

        opcijaDto.setId(opcija.getId());//
        opcijaDto.setStrikePrice(opcija.getStrikePrice());//
        opcijaDto.setLastPrice(opcija.getLastPrice());//
        opcijaDto.setBid(opcija.getBid());//
        opcijaDto.setContractSymbol(opcija.getContractSymbol());//
        opcijaDto.setOptionType(opcija.getOptionType());//
        opcijaDto.setAsk(opcija.getAsk());//
        opcijaDto.setTicker(opcija.getTicker());//
        opcijaDto.setChange(opcija.getChange());//
        opcijaDto.setPercentChange(opcija.getPercentChange());//
        opcijaDto.setImpliedVolatility(opcija.getImpliedVolatility());//
        opcijaDto.setOpenInterest(opcija.getOpenInterest());//
        opcijaDto.setContractSize(opcija.getContractSize());//

        opcijaDto.setInTheMoney(opcija.isInTheMoney());
        opcijaDto.setExpiration(opcija.getExpiration());
        opcijaDto.setDatumIstekaVazenja(LocalDateTime.ofInstant(Instant.ofEpochSecond(opcija.getExpiration()),ZoneOffset.systemDefault()));


        return opcijaDto;
    }

    public Opcija yahooOpcijaToOpcija(OptionYahooApiMap optionYahooApiMap){

        Opcija opcija = new Opcija();

        opcija.setStrikePrice(optionYahooApiMap.getStrike());
        opcija.setLastPrice(optionYahooApiMap.getLastPrice());
        opcija.setBid(optionYahooApiMap.getBid());
        opcija.setContractSymbol(optionYahooApiMap.getContractSymbol());
        opcija.setOptionType(optionYahooApiMap.getOpcijaTip());
        opcija.setAsk(optionYahooApiMap.getAsk());
        opcija.setTicker(optionYahooApiMap.getTicker());
        opcija.setChange(optionYahooApiMap.getChange());
        opcija.setPercentChange(optionYahooApiMap.getPercentChange());
        opcija.setInTheMoney(optionYahooApiMap.getInTheMoney());
        opcija.setImpliedVolatility(optionYahooApiMap.getImpliedVolatility());
        opcija.setOpenInterest(optionYahooApiMap.getOpenInterest());
        opcija.setContractSize(optionYahooApiMap.getContractSize().equals("REGULAR")?100:0);
        opcija.setExpiration(optionYahooApiMap.getExpiration());

        //PREDEFINISANO JER NEMA NA API
        opcija.setTrenutnaCenaOsnovneAkcijeKompanije(60);
        opcija.setUkupanBrojIzdatihAkcijaKompanije(1000);
        ////////////////

        opcija.setDatumIstekaVazenja(LocalDateTime.ofInstant(Instant.ofEpochSecond(opcija.getExpiration()),ZoneOffset.systemDefault()));


        LocalDateTime trenutnoVreme = LocalDateTime.now();

        if(opcija.getDatumIstekaVazenja().isBefore(trenutnoVreme)) {
            opcija.setOpcijaStanje(OpcijaStanje.EXPIRED);
            //opcija.setIstaIstorijaGroupId();
        }

        //POTREBNI PARAMETRI ZA IZVEDENE VREDNOSTI
        //trenutnaCenaOsnovneAkcijeKompanije//nema na api
        //strikePrice
        //impliedVolatility
        //expiration
        //ukupanBrojIzdatihAkcijaKompanije//nema na api

        opcija.izracunajIzvedeneVrednosti(izvedeneVrednostiUtil);

        return opcija;
    }

}
