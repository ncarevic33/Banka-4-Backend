package rs.edu.raf.opcija.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OptionChainResponse {
    @JsonProperty("optionChain")
    private OptionChain optionChain;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class OptionChain {
        @JsonProperty("result")
        private List<OptionChainResult> result;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class OptionChainResult {
        @JsonProperty("underlyingSymbol")
        private String underlyingSymbol;

        @JsonProperty("expirationDates")
        private List<Long> expirationDates;

        @JsonProperty("strikes")
        private List<Double> strikes;

        @JsonProperty("hasMiniOptions")
        private boolean hasMiniOptions;

        @JsonProperty("quote")
        private Quote quote;

        @JsonProperty("options")
        private List<Option> options;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Quote {
        @JsonProperty("language")
        private String language;

        @JsonProperty("region")
        private String region;

        @JsonProperty("quoteType")
        private String quoteType;

        @JsonProperty("triggerable")
        private boolean triggerable;

        @JsonProperty("quoteSourceName")
        private String quoteSourceName;

        @JsonProperty("epsTrailingTwelveMonths")
        private double epsTrailingTwelveMonths;

        @JsonProperty("epsForward")
        private double epsForward;

        @JsonProperty("bookValue")
        private double bookValue;

        @JsonProperty("fiftyDayAverage")
        private double fiftyDayAverage;

        @JsonProperty("fiftyDayAverageChange")
        private double fiftyDayAverageChange;

        @JsonProperty("fiftyDayAverageChangePercent")
        private double fiftyDayAverageChangePercent;

        @JsonProperty("twoHundredDayAverage")
        private double twoHundredDayAverage;

        @JsonProperty("priceToBook")
        private double priceToBook;

        @JsonProperty("fiftyTwoWeekLowChange")
        private double fiftyTwoWeekLowChange;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("messageBoardId")
        private String messageBoardId;

        @JsonProperty("fullExchangeName")
        private String fullExchangeName;

        @JsonProperty("marketCap")
        private long marketCap;

        @JsonProperty("forwardPE")
        private double forwardPE;

        @JsonProperty("earningsTimestamp")
        private long earningsTimestamp;

        @JsonProperty("earningsTimestampStart")
        private long earningsTimestampStart;

        @JsonProperty("fiftyTwoWeekLowChangePercent")
        private double fiftyTwoWeekLowChangePercent;

        @JsonProperty("esgPopulated")
        private boolean esgPopulated;

        @JsonProperty("regularMarketChangePercent")
        private double regularMarketChangePercent;

        @JsonProperty("fiftyTwoWeekRange")
        private String fiftyTwoWeekRange;

        @JsonProperty("fiftyTwoWeekHighChange")
        private double fiftyTwoWeekHighChange;

        @JsonProperty("fiftyTwoWeekHighChangePercent")
        private double fiftyTwoWeekHighChangePercent;

        @JsonProperty("epsCurrentYear")
        private double epsCurrentYear;

        @JsonProperty("sourceInterval")
        private int sourceInterval;

        @JsonProperty("exchangeTimezoneName")
        private String exchangeTimezoneName;

        @JsonProperty("exchangeTimezoneShortName")
        private String exchangeTimezoneShortName;

        @JsonProperty("earningsTimestampEnd")
        private long earningsTimestampEnd;

        @JsonProperty("trailingAnnualDividendRate")
        private double trailingAnnualDividendRate;

        @JsonProperty("trailingPE")
        private double trailingPE;

        @JsonProperty("tradeable")
        private boolean tradeable;

        @JsonProperty("sharesOutstanding")
        private long sharesOutstanding;

        @JsonProperty("regularMarketDayRange")
        private String regularMarketDayRange;

        @JsonProperty("twoHundredDayAverageChange")
        private double twoHundredDayAverageChange;

        @JsonProperty("twoHundredDayAverageChangePercent")
        private double twoHundredDayAverageChangePercent;

        @JsonProperty("priceEpsCurrentYear")
        private double priceEpsCurrentYear;

        @JsonProperty("shortName")
        private String shortName;

        @JsonProperty("gmtOffSetMilliseconds")
        private long gmtOffSetMilliseconds;

        @JsonProperty("exchangeDataDelayedBy")
        private int exchangeDataDelayedBy;

        @JsonProperty("regularMarketPreviousClose")
        private double regularMarketPreviousClose;

        @JsonProperty("bid")
        private double bid;

        @JsonProperty("ask")
        private double ask;

        @JsonProperty("bidSize")
        private int bidSize;

        @JsonProperty("askSize")
        private int askSize;

        @JsonProperty("fiftyTwoWeekLow")
        private double fiftyTwoWeekLow;

        @JsonProperty("fiftyTwoWeekHigh")
        private double fiftyTwoWeekHigh;

        @JsonProperty("dividendDate")
        private long dividendDate;

        @JsonProperty("marketState")
        private String marketState;

        @JsonProperty("regularMarketPrice")
        private double regularMarketPrice;

        @JsonProperty("regularMarketTime")
        private long regularMarketTime;

        @JsonProperty("regularMarketChange")
        private double regularMarketChange;

        @JsonProperty("regularMarketOpen")
        private double regularMarketOpen;

        @JsonProperty("regularMarketDayHigh")
        private double regularMarketDayHigh;

        @JsonProperty("regularMarketDayLow")
        private double regularMarketDayLow;

        @JsonProperty("regularMarketVolume")
        private long regularMarketVolume;

        @JsonProperty("exchange")
        private String exchange;

        @JsonProperty("longName")
        private String longName;

        @JsonProperty("financialCurrency")
        private String financialCurrency;

        @JsonProperty("averageDailyVolume3Month")
        private long averageDailyVolume3Month;

        @JsonProperty("averageDailyVolume10Day")
        private long averageDailyVolume10Day;

        @JsonProperty("trailingAnnualDividendYield")
        private double trailingAnnualDividendYield;

        @JsonProperty("market")
        private String market;

        @JsonProperty("priceHint")
        private int priceHint;

        @JsonProperty("symbol")
        private String symbol;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Option {
        @JsonProperty("expirationDate")
        private long expirationDate;

        @JsonProperty("hasMiniOptions")
        private boolean hasMiniOptions;

        @JsonProperty("calls")
        private List<Contract> calls;

        @JsonProperty("puts")
        private List<Contract> puts;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Contract {
        @JsonProperty("contractSymbol")
        private String contractSymbol;

        @JsonProperty("strike")
        private double strike;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("lastPrice")
        private double lastPrice;

        @JsonProperty("change")
        private double change;

        @JsonProperty("percentChange")
        private double percentChange;

        @JsonProperty("volume")
        private int volume;

        @JsonProperty("openInterest")
        private int openInterest;

        @JsonProperty("bid")
        private double bid;

        @JsonProperty("ask")
        private double ask;

        @JsonProperty("contractSize")
        private String contractSize;

        @JsonProperty("expiration")
        private long expiration;

        @JsonProperty("lastTradeDate")
        private long lastTradeDate;

        @JsonProperty("impliedVolatility")
        private double impliedVolatility;

        @JsonProperty("inTheMoney")
        private boolean inTheMoney;
    }


}
