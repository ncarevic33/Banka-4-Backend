package rs.edu.raf.berza.opcija.servis.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.edu.raf.berza.opcija.model.OpcijaTip;

@Data
@NoArgsConstructor
public class OptionYahooApiMap {

    private String contractSymbol;
    private double strike;
    private String currency;
    private double lastPrice;
    private double change;
    private double percentChange;
    private double bid;
    private double ask;
    private String contractSize;
    private long expiration;//broj sekundi od 1970
    private long lastTradeDate;//broj sekundi od 1970
    private double impliedVolatility;
    private boolean inTheMoney;

    private String ticker;
    private OpcijaTip opcijaTip;

    private long volume;
    private long openInterest;

}
