package rs.edu.raf.berza.opcija.servis.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//za pristup aktuelnim podacima opcije
public class FinansijaApiUtil {

    private final static Logger log = LoggerFactory.getLogger(FinansijaApiUtil.class.getSimpleName());
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public static List<OptionYahooApiMap> fetchOptionsFromYahooApi(List<String> tickers) throws IOException {

        List<OptionYahooApiMap> allYahooTickersOptions = new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        for(String ticker : tickers) {
            HttpGet httpGet = new HttpGet("https://query1.finance.yahoo.com/v6/finance/options/" + ticker);
            CloseableHttpResponse responseOptions = httpClient.execute(httpGet);
            allYahooTickersOptions.addAll(JsonParserUtil.parseBytesToYahooOptionObject(responseOptions.getEntity().getContent(), ticker));
            responseOptions.close();
        }
        validateOptions(allYahooTickersOptions);
        //System.out.println(allTickersOptions);
        return allYahooTickersOptions;
    }
    private static void validateOptions(List<OptionYahooApiMap> options) {
        Set<ConstraintViolation<OptionYahooApiMap>> violations;
        for (OptionYahooApiMap option : options) {
            violations = validator.validate(option);
            if (!violations.isEmpty()) {
                throw new RuntimeException("Validacija nije uspela: " + violations.toString());
            }
        }
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
