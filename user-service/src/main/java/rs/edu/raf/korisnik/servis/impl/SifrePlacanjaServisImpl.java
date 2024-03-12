package rs.edu.raf.korisnik.servis.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.raf.korisnik.dto.SifrePlacanjaDTO;
import rs.edu.raf.korisnik.model.SifrePlacanja;
import rs.edu.raf.korisnik.repository.SifrePlacanjaRepository;
import rs.edu.raf.korisnik.servis.SifrePlacanjaMapper;
import rs.edu.raf.korisnik.servis.SifrePlacanjaServis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SifrePlacanjaServisImpl implements SifrePlacanjaServis {

    @Autowired
    private SifrePlacanjaRepository sifrePlacanjaRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    public List<SifrePlacanjaDTO> readAndProcessFile() {
        List<SifrePlacanjaDTO> data = new ArrayList<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:sifrePlacanja.csv");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                data.add(SifrePlacanjaDTO.builder()
                        .oblikIOsnov(Integer.valueOf(parts[0]))
                        .opisPlacanja(parts[1])
                        .build());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Transactional
    @PostConstruct
    public void init() {

        List<SifrePlacanjaDTO> sifrePlacanjaDTOS = readAndProcessFile();

        List<SifrePlacanja> sifrePlacanja = new ArrayList<>();
        for(SifrePlacanjaDTO sifrePlacanjaDTO : sifrePlacanjaDTOS){
            sifrePlacanja.add(SifrePlacanjaMapper.toEntity(sifrePlacanjaDTO));
        }
        sifrePlacanjaRepository.saveAll(sifrePlacanja);
    }

    //PROVERITI
    @Override
    public SifrePlacanja findById(Long id) {
        return null;
    }

    @Override
    public SifrePlacanja findByOblikAndOsnov(Integer oblikIOsnov) {
        return null;
    }
}
