package rs.edu.raf.currency.servis.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.edu.raf.currency.dto.InflationDTO;
import rs.edu.raf.currency.mapper.InflationMapper;
import rs.edu.raf.currency.model.Currency;
import rs.edu.raf.currency.model.Inflation;
import rs.edu.raf.currency.repository.CurrencyRepository;
import rs.edu.raf.currency.repository.InflationRepository;
import rs.edu.raf.currency.servis.InflationService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class InflationServiceImpl implements InflationService {

    InflationRepository inflationRepository;
    CurrencyRepository currencyRepository;
    InflationMapper inflationMapper;

    @Autowired
    public InflationServiceImpl(InflationRepository inflationRepository, CurrencyRepository currencyRepository, InflationMapper inflationMapper) {
        this.inflationRepository = inflationRepository;
        this.inflationMapper = inflationMapper;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<InflationDTO> getInflationByCountry(String country) {
        List<Inflation> infl = inflationRepository.findAllByCountry(country);
        List<InflationDTO> dtos = new ArrayList<>();
        for (Inflation i : infl) {
            InflationDTO dto = inflationMapper.inflationToInflationDTO(i);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<InflationDTO> getInflationByCountryAndYear(String country, String year) {
        List<Inflation> infl = inflationRepository.findAllByCountryAndInflYear(country, year);
        List<InflationDTO> dtos = new ArrayList<>();
        for (Inflation i : infl) {
            InflationDTO dto = inflationMapper.inflationToInflationDTO(i);
            dtos.add(dto);
        }
        return dtos;
    }

    @PostConstruct //COMMENT ME OUT AFTER THE FIRST RUN!
    private void loadInflationData() { //SVI podaci
        inflAPICall(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH"));
    }

    @Scheduled(cron = "0 5 10 1 * *")
    private void updateInflationData() { //trazi jedino podatke za trenutnu godinu
        String yr = String.valueOf(new GregorianCalendar().get(Calendar.YEAR));
        inflAPICall(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH?periods="+yr));
    }

    private void inflAPICall(URI uri) {
        List<Inflation> infl = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String body = response.body();

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
            Inflation i = new Inflation();
            i.setCountry(country.getKey());
            for (JsonNode y : c) {
                Iterator<Map.Entry<String, JsonNode>> years = y.fields();
                while (years.hasNext()) {
                    Map.Entry<String, JsonNode> year = years.next();
                    i.setInflYear(year.getKey());
                    i.setInflationRate(String.valueOf(year.getValue()));
                    infl.add(i);
                }
            }
        }
        inflationRepository.saveAll(infl);
    }
}
