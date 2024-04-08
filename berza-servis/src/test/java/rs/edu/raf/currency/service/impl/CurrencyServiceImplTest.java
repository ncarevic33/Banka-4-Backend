package rs.edu.raf.currency.service.impl;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.edu.raf.currency.model.Currency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CurrencyServiceImplTest {

    @Test
    public void APISuccessTest() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.alphavantage.co/physical_currency_list/")).build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(httpResponse.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void processInflationDataTest() throws IOException, InterruptedException {
        List<Currency> currencies = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.alphavantage.co/physical_currency_list/")).build();
        HttpResponse<String> response = null;         // FVHPQRIRKBYHSTJU
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String body = response.body();
        String[] lines = body.substring(body.indexOf("\n")+1).split("\n");
        for (String l : lines) {
            String[] c = l.split(",");
            Currency currency = new Currency();
            currency.setCurrencyCode(c[0]);
            currency.setCurrencyName(c[1]);
            try {
                currency.setCurrencySymbol(java.util.Currency.getInstance(c[0]).getSymbol()); //with locale? needs country codes
            } catch (NullPointerException | IllegalArgumentException e) {
                currency.setCurrencySymbol(c[0]);
            }
            currency.setPolity(c[0].substring(0,2)); //country code api?
            currencies.add(currency);
        }
        System.out.println(currencies);
    }
}
