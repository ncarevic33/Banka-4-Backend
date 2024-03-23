import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.DependsOn;
import rs.edu.raf.berza.opcija.model.OpcijaTip;
import rs.edu.raf.berza.opcija.servis.IzvedeneVrednostiUtil;
import rs.edu.raf.berza.opcija.servis.util.FinansijaApiUtil;
import rs.edu.raf.berza.opcija.servis.util.OptionYahooApiMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(FinansijaApiUtil.class)
public class OpcijaServisIntegationTest {


    @Test
    public void testYahooApiDeserialization() throws IOException {
        String json = "{" +
                "\"optionChain\": {" +
                "\"result\": [" +
                "{" +
                "\"underlyingSymbol\": \"AAPL\"," +
                "\"expirationDates\": [" +
                "1711065600," +
                "1711584000," +
                "1712275200," +
                "1712880000," +
                "1713484800," +
                "1714089600," +
                "1715904000," +
                "1718928000," +
                "1721347200," +
                "1723766400," +
                "1726790400," +
                "1729209600," +
                "1731628800," +
                "1734652800," +
                "1737072000," +
                "1742515200," +
                "1750377600," +
                "1758240000," +
                "1766102400," +
                "1768521600," +
                "1781740800" +
                "]," +
                "\"strikes\": [" +
                "100.0," +
                "105.0," +
                "110.0," +
                "115.0," +
                "120.0," +
                "125.0," +
                "130.0," +
                "135.0," +
                "140.0," +
                "145.0," +
                "146.0," +
                "147.0," +
                "148.0," +
                "149.0," +
                "150.0," +
                "152.5," +
                "155.0," +
                "157.5," +
                "160.0," +
                "162.5," +
                "165.0," +
                "167.5," +
                "170.0," +
                "172.5," +
                "175.0," +
                "177.5," +
                "180.0," +
                "182.5," +
                "185.0," +
                "187.5," +
                "190.0," +
                "192.5," +
                "195.0," +
                "197.5," +
                "200.0," +
                "202.5," +
                "205.0," +
                "210.0," +
                "215.0," +
                "220.0," +
                "225.0," +
                "230.0," +
                "235.0," +
                "240.0," +
                "245.0," +
                "255.0," +
                "260.0," +
                "265.0" +
                "]," +
                "\"hasMiniOptions\": false," +
                "\"quote\": {" +
                "\"language\": \"en-US\"," +
                "\"region\": \"US\"," +
                "\"quoteType\": \"EQUITY\"," +
                "\"triggerable\": true," +
                "\"quoteSourceName\": \"Nasdaq Real Time Price\"," +
                "\"currency\": \"USD\"," +
                "\"shortName\": \"Apple Inc.\"," +
                "\"sharesOutstanding\": 15441899520," +
                "\"bookValue\": 4.793," +
                "\"fiftyDayAverage\": 183.2858," +
                "\"fiftyDayAverageChange\": -10.665802," +
                "\"fiftyDayAverageChangePercent\": -0.05819219," +
                "\"twoHundredDayAverage\": 183.8347," +
                "\"twoHundredDayAverageChange\": -11.214706," +
                "\"twoHundredDayAverageChangePercent\": -0.061004296," +
                "\"marketCap\": 2665580593152," +
                "\"forwardPE\": 24.108938," +
                "\"priceToBook\": 36.01502," +
                "\"sourceInterval\": 15," +
                "\"exchangeTimezoneName\": \"America/New_York\"," +
                "\"exchangeTimezoneShortName\": \"EDT\"," +
                "\"gmtOffSetMilliseconds\": -14400000," +
                "\"priceHint\": 2," +
                "\"preMarketChange\": 1.1000061," +
                "\"preMarketChangePercent\": 0.6372414," +
                "\"preMarketTime\": 1710749371," +
                "\"preMarketPrice\": 173.72," +
                "\"longName\": \"Apple Inc.\"," +
                "\"financialCurrency\": \"USD\"," +
                "\"averageDailyVolume3Month\": 58545398," +
                "\"averageDailyVolume10Day\": 76014170," +
                "\"fiftyTwoWeekLowChange\": 18.470001," +
                "\"fiftyTwoWeekLowChangePercent\": 0.119818375," +
                "\"fiftyTwoWeekRange\": \"154.15 - 199.62\"," +
                "\"fiftyTwoWeekHighChange\": -27.0," +
                "\"fiftyTwoWeekHighChangePercent\": -0.13525699," +
                "\"fiftyTwoWeekLow\": 154.15," +
                "\"fiftyTwoWeekHigh\": 199.62," +
                "\"market\": \"us_market\"," +
                "\"esgPopulated\": false," +
                "\"tradeable\": false," +
                "\"marketState\": \"PRE\"," +
                "\"regularMarketChangePercent\": -0.21965599," +
                "\"regularMarketDayRange\": \"170.29 - 172.62\"," +
                "\"regularMarketPreviousClose\": 173.0," +
                "\"bid\": 173.08," +
                "\"ask\": 173.09," +
                "\"bidSize\": 12," +
                "\"askSize\": 11," +
                "\"messageBoardId\": \"finmb_24937\"," +
                "\"fullExchangeName\": \"NasdaqGS\"," +
                "\"regularMarketPrice\": 172.62," +
                "\"regularMarketTime\": 1710532802," +
                "\"regularMarketChange\": -0.38000488," +
                "\"regularMarketOpen\": 171.0," +
                "\"regularMarketDayHigh\": 172.62," +
                "\"regularMarketDayLow\": 170.29," +
                "\"regularMarketVolume\": 121752699," +
                "\"exchange\": \"NMS\"," +
                "\"dividendDate\": 1707955200," +
                "\"earningsTimestamp\": 1706824800," +
                "\"earningsTimestampStart\": 1714647540," +
                "\"earningsTimestampEnd\": 1714996800," +
                "\"trailingAnnualDividendRate\": 0.95," +
                "\"trailingPE\": 26.804346," +
                "\"trailingAnnualDividendYield\": 0.0054913294," +
                "\"epsTrailingTwelveMonths\": 6.44," +
                "\"epsForward\": 7.16," +
                "\"epsCurrentYear\": 6.56," +
                "\"priceEpsCurrentYear\": 26.314024," +
                "\"exchangeDataDelayedBy\": 0," +
                "\"symbol\": \"AAPL\"" +
                "},"+"\"options\": [" +
                "{" +
                "\"expirationDate\": 1711065600," +
                "\"hasMiniOptions\": false," +
                "\"calls\": [" +
                "{" +
                "\"contractSymbol\": \"AAPL240322C00100000\"," +
                "\"strike\": 100.0," +
                "\"currency\": \"USD\"," +
                "\"lastPrice\": 73.01," +
                "\"change\": 0.0," +
                "\"percentChange\": 0.0," +
                "\"volume\": 5," +
                "\"openInterest\": 0," +
                "\"bid\": 0.0," +
                "\"ask\": 0.0," +
                "\"contractSize\": \"REGULAR\"," +
                "\"expiration\": 1711065600," +
                "\"lastTradeDate\": 1710445704," +
                "\"impliedVolatility\": 1.0000000000000003E-5," +
                "\"inTheMoney\": true" +
                "}" +
                "]" +
                "}" +
                "]" +
                "}";

        CloseableHttpResponse responseMock = mock(CloseableHttpResponse.class);
        CloseableHttpClient httpClientMock = mock(CloseableHttpClient.class);
        when(httpClientMock.execute(any(HttpGet.class))).thenReturn(responseMock);

        InputStream mockInputStream = new ByteArrayInputStream(json.getBytes());
        when(responseMock.getEntity().getContent()).thenReturn(mockInputStream);


        PowerMockito.mockStatic(FinansijaApiUtil.class);
        when(FinansijaApiUtil.fetchOptionsFromYahooApi(Arrays.asList(""))).thenCallRealMethod(); // Poziv realne metode


        List<OptionYahooApiMap> result = FinansijaApiUtil.fetchOptionsFromYahooApi(Arrays.asList(""));
        OptionYahooApiMap firstOption = result.get(0);

        assertNotNull("yahoo objekat ne sme biti null",result);

        assertNotNull("contractSymbol ne sme biti null", firstOption.getContractSymbol());
        assertNotNull("strike ne sme biti null", firstOption.getStrike());
        assertNotNull("currency ne sme biti null", firstOption.getCurrency());
        assertNotNull("lastPrice ne sme biti null", firstOption.getLastPrice());
        assertNotNull("change ne sme biti null", firstOption.getChange());
        assertNotNull("percentChange ne sme biti null", firstOption.getPercentChange());
        assertNotNull("bid ne sme biti null", firstOption.getBid());
        assertNotNull("ask ne sme biti null", firstOption.getAsk());
        assertNotNull("contractSize ne sme biti null", firstOption.getContractSize());
        assertNotNull("expiration ne sme biti null", firstOption.getExpiration());
        assertNotNull("lastTradeDate ne sme biti null", firstOption.getLastTradeDate());
        assertNotNull("impliedVolatility ne sme biti null", firstOption.getImpliedVolatility());
        assertNotNull("inTheMoney ne sme biti null", firstOption.getInTheMoney());
        assertNotNull("ticker ne sme biti null", firstOption.getTicker());
        assertNotNull("opcijaTip ne sme biti null", firstOption.getOpcijaTip());

        assertEquals("AAPL240322C00100000", firstOption.getContractSymbol());
        assertEquals(Double.parseDouble("100.0"), Optional.ofNullable(firstOption.getStrike()));
        assertEquals("USD", firstOption.getCurrency());
        assertEquals(Double.parseDouble("73.01"), Optional.ofNullable(firstOption.getLastPrice()));
        assertEquals(Double.parseDouble("0.0"), Optional.ofNullable(firstOption.getChange()));
        assertEquals(Double.parseDouble("0.0"), Optional.ofNullable(firstOption.getPercentChange()));
        assertEquals(Double.parseDouble("0.0"), Optional.ofNullable(firstOption.getBid()));
        assertEquals(Double.parseDouble("0.0"), Optional.ofNullable(firstOption.getAsk()));
        assertEquals("REGULAR", firstOption.getContractSize());
        assertEquals(Long.parseLong("1711065600"), Optional.ofNullable(firstOption.getExpiration()));
        assertEquals(Long.parseLong("1710445704"), Optional.ofNullable(firstOption.getLastTradeDate()));
        assertEquals(Double.parseDouble("1.0000000000000003E-5"), Optional.ofNullable(firstOption.getImpliedVolatility()));
        assertEquals(Boolean.parseBoolean("true"), firstOption.getInTheMoney());
        assertEquals("", firstOption.getTicker());
        assertEquals(OpcijaTip.CALL, firstOption.getOpcijaTip());
    }

    @Test
    @DependsOn("testYahooApiDeserialization")//ovaj test zavisi od prethodnog testa
    public void testYahooApiFetchNotNull() throws IOException {

        PowerMockito.mockStatic(FinansijaApiUtil.class);
        when(FinansijaApiUtil.fetchOptionsFromYahooApi(anyList())).thenCallRealMethod(); // Poziv realne metode

        List<OptionYahooApiMap> result = FinansijaApiUtil.fetchOptionsFromYahooApi(Arrays.asList("AAPL"));

        assertNotNull(result);

    }

    @Test
    public void testPolygonApiDeserialization() throws IOException {

        String json = "{" +
                "\"results\": [" +
                "{" +
                "\"ticker\": \"A\"," +
                "\"name\": \"Agilent Technologies Inc.\"," +
                "\"market\": \"stocks\"," +
                "\"locale\": \"us\"," +
                "\"primary_exchange\": \"XNYS\"," +
                "\"type\": \"CS\"," +
                "\"active\": true," +
                "\"currency_name\": \"usd\"," +
                "\"cik\": \"0001090872\"," +
                "\"composite_figi\": \"BBG000C2V3D6\"," +
                "\"share_class_figi\": \"BBG001SCTQY4\"," +
                "\"last_updated_utc\": \"2024-03-15T00:00:00Z\"" +
                "}" +
                "]" +
                "}";


        CloseableHttpResponse responseMock = mock(CloseableHttpResponse.class);
        CloseableHttpClient httpClientMock = mock(CloseableHttpClient.class);
        when(httpClientMock.execute(any(HttpGet.class))).thenReturn(responseMock);

        InputStream mockInputStream = new ByteArrayInputStream(json.getBytes());
        when(responseMock.getEntity().getContent()).thenReturn(mockInputStream);


        PowerMockito.mockStatic(FinansijaApiUtil.class);
        when(FinansijaApiUtil.fetchTickerNames()).thenCallRealMethod(); // Poziv realne metode


        List<String> result = FinansijaApiUtil.fetchTickerNames();

        assertNotNull("ticker objekat ne sme biti null",result.get(0));
        assertEquals("A",result.get(0));

    }

    @Test
    @DependsOn("testPolygonApiDeserialization")
    public void testPolygonApiFetchNotNull() throws IOException {

        PowerMockito.mockStatic(FinansijaApiUtil.class);
        when(FinansijaApiUtil.fetchTickerNames()).thenCallRealMethod(); // Poziv realne metode

        List<String> result = FinansijaApiUtil.fetchTickerNames();

        assertNotNull(result);

    }

    @Test
    public void testGenerateOptionBlackScholes(){

        double trenutnaCenaOsnovneAkcije = 100.0;
        double cenaIzvrsenjaOpcije = 90.0;
        double stopaBezRizika = 0.05;
        double brojGodinaDoIstekaOpcije = 1.0;
        double volatilnostCeneOsnovneAkcije = 0.2;

        PowerMockito.mockStatic(IzvedeneVrednostiUtil.class);
        when(IzvedeneVrednostiUtil.calculateBlackScholesValue(
                trenutnaCenaOsnovneAkcije, cenaIzvrsenjaOpcije, stopaBezRizika,
                brojGodinaDoIstekaOpcije, volatilnostCeneOsnovneAkcije))
                .thenCallRealMethod();

        // Poziv metode koju testiramo
        double result = IzvedeneVrednostiUtil.calculateBlackScholesValue(trenutnaCenaOsnovneAkcije, cenaIzvrsenjaOpcije,
                stopaBezRizika, brojGodinaDoIstekaOpcije, volatilnostCeneOsnovneAkcije);

        // Provjera očekivanog rezultata
        assertEquals(12.601586582153017, result, 0.0001);







    }

}
