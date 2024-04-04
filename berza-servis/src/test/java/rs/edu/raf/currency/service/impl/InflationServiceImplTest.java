package rs.edu.raf.currency.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.edu.raf.currency.model.Inflation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class InflationServiceImplTest {

    @Test
    public void APISuccessTest() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH")).build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(httpResponse.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void APISuccessTestYear() throws IOException, InterruptedException {
        String yr = String.valueOf(new GregorianCalendar().get(Calendar.YEAR));
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH?periods="+yr)).build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(httpResponse.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void processInflationDataTest() throws IOException, InterruptedException {
        List<Inflation> infl = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH")).build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String body = httpResponse.body();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode c = root.get("values").get("PCPIPCH");
        Iterator<Map.Entry<String, JsonNode>> countries = c.fields();
        while (countries.hasNext()) {
            Map.Entry<String, JsonNode> country = countries.next();
            for (Iterator<Map.Entry<String, JsonNode>> it = country.getValue().fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> y = it.next();
                Inflation i = new Inflation();
                i.setCountry(country.getKey());
                i.setInflYear(y.getKey());
                i.setInflationRate(String.valueOf(y.getValue()));
                infl.add(i);
            }
        }
        System.out.println(infl);
    }

    /*@Given("request for all inflation data")
    public void makeRequest() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH")).build();
    }

    @When("get response")
    public void getResponse() {
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("")
    public void checkResponse() {
        inflationRepository.saveAll(infl);
    }*/
}
