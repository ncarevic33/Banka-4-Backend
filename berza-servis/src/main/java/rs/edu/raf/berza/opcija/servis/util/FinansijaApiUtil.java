package rs.edu.raf.berza.opcija.servis.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//za pristup aktuelnim podacima opcije
public class FinansijaApiUtil {

    private final static Logger log = LoggerFactory.getLogger(FinansijaApiUtil.class.getSimpleName());

    public static List<OptionYahooApiMap> fetchOptionsFromYahooApi(List<String> tickers) throws IOException {

        List<OptionYahooApiMap> allTickersOptions = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        for(String ticker : tickers) {
            HttpGet httpGet = new HttpGet("https://query1.finance.yahoo.com/v6/finance/options/" + ticker);
            CloseableHttpResponse responseOptions = httpClient.execute(httpGet);
            allTickersOptions.addAll(JsonParserUtil.parseBytesToOptionObject(responseOptions.getEntity().getContent(), ticker));
            responseOptions.close();
        }

        //System.out.println(allTickersOptions);
        return allTickersOptions;
    }
    public static List<String> fetchTickerNames() throws IOException {

        List<String> allTickerNames = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://api.polygon.io/v3/reference/tickers?active=true&apiKey=sD9RXYv12OWqAVg8ovsgtVaI91l988Op");
        //api key je token(kao da si poslao username i password)
        //httpGet.setHeader("Authorization","sD9RXYv12OWqAVg8ovsgtVaI91l988Op");
        HttpResponse httpResponse = httpClient.execute(httpGet);

        allTickerNames.addAll(JsonParserUtil.parseBytesToTickerNames(httpResponse.getEntity().getContent()));

        //log.info(String.valueOf(allTickerNames));

        return allTickerNames;

    }

}
