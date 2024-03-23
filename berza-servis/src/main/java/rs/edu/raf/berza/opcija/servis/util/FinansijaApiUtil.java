package rs.edu.raf.berza.opcija.servis.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
//za pristup aktuelnim podacima opcije
public class FinansijaApiUtil {

    private final static Logger log = LoggerFactory.getLogger(FinansijaApiUtil.class.getSimpleName());
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private JsonParserUtil jsonParserUtil;

    //OBAVEZNO OVDE GLOBALNO REFERENCIRANJE ODNOSNO IZVAN TESTIRANE METODE DA BISMO MOGLI DA IM SERUJEMO MOCK
    //OBAVEZNO OVDE INSTANCIRANJA AKO POSTOJE ZBOG MOCKOVANJA
    //JER AKO SU U METODI ONDA CE BITI MOCK OVERIDOVAN PA NECE RADITI when(mock)..thenReturn..
    CloseableHttpResponse response;
    CloseableHttpClient  httpClient = HttpClients.createDefault();;
    HttpGet httpGet = null;

    public List<OptionYahooApiMap> fetchOptionsFromYahooApi(List<String> tickers) throws IOException {

        List<OptionYahooApiMap> allYahooTickersOptions = new ArrayList<>();

        for(String ticker : tickers) {
            if(httpGet==null)
                httpGet = new HttpGet("https://query1.finance.yahoo.com/v6/finance/options/" + ticker);

            response = httpClient.execute(httpGet);
            allYahooTickersOptions.addAll(jsonParserUtil.parseBytesToYahooOptionObject(response.getEntity().getContent(), ticker));
            response.close();
        }
        validateOptions(allYahooTickersOptions);
        //System.out.println(allTickersOptions);
        httpGet = null;

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
    public List<String> fetchTickerNames() throws IOException {

        List<String> allTickerNames = new ArrayList<>();

        //api key je token(kao da si poslao username i password)
        //httpGet.setHeader("Authorization","sD9RXYv12OWqAVg8ovsgtVaI91l988Op");
        if(httpGet==null)
            httpGet = new HttpGet("https://api.polygon.io/v3/reference/tickers?active=true&apiKey=sD9RXYv12OWqAVg8ovsgtVaI91l988Op");

        response = httpClient.execute(httpGet);

        allTickerNames.addAll(jsonParserUtil.parseBytesToTickerNames(response.getEntity().getContent()));

        //log.info(String.valueOf(allTickerNames));

        httpGet = null;

        return allTickerNames;

    }

}
