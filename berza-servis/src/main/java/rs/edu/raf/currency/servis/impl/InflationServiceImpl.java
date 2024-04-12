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
import rs.edu.raf.currency.model.Inflation;
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
    InflationMapper inflationMapper;

    @Autowired
    public InflationServiceImpl(InflationRepository inflationRepository, InflationMapper inflationMapper) {
        this.inflationRepository = inflationRepository;
        this.inflationMapper = inflationMapper;
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

    @Scheduled(initialDelay = 60000L)
    private void load(){
        inflAPICall(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH"));
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
/*
    @PostConstruct //COMMENT ME OUT AFTER THE FIRST RUN!
    private void loadInflationData() { //SVI podaci
        inflAPICall(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH"));
    }
*/
    @Scheduled(cron = "0 5 10 1 * *")
    private void updateInflationData() { //trazi jedino podatke za trenutnu godinu
        String yr = String.valueOf(new GregorianCalendar().get(Calendar.YEAR));
        inflAPICall(URI.create("https://www.imf.org/external/datamapper/api/v1/PCPIPCH?periods="+yr));
    }

    private void inflAPICall(URI uri) {
        System.out.println("1");
        List<Inflation> infl = new ArrayList<>();

        System.out.println("2");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = null;
        try {
            System.out.println("3");
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("4");
            //throw new RuntimeException(e);
        }
            System.out.println("5");
        String body = response.body();

            System.out.println("6");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            System.out.println("7");
            root = mapper.readTree(body);
        } catch (JsonProcessingException e) {
            System.out.println("8");
            throw new RuntimeException(e);
        }

            System.out.println("9");
        JsonNode c = root.get("values").get("PCPIPCH");
        Iterator<Map.Entry<String, JsonNode>> countries = c.fields();
            System.out.println("10");
        while (countries.hasNext()) {
            Map.Entry<String, JsonNode> country = countries.next();
            System.out.println("11");
            for (Iterator<Map.Entry<String, JsonNode>> it = country.getValue().fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> y = it.next();
                Inflation i = new Inflation();
                i.setCountry(country.getKey());
                i.setInflYear(y.getKey());
                i.setInflationRate(String.valueOf(y.getValue()));
                infl.add(i);
            }
        }
        inflationRepository.saveAll(infl);
    }
}
