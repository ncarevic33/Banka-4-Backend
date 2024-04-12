package rs.edu.raf.currency.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.currency.dto.CurrencyDTO;
import rs.edu.raf.currency.dto.InflationDTO;
import rs.edu.raf.currency.servis.InflationService;

import java.util.List;

@RestController
@RequestMapping("/inflation")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class InflationController {
    @Autowired
    private InflationService inflationService;

    @ApiOperation(value = "List the inflation rates of a country")
    @GetMapping("/get/{country}")
    public ResponseEntity<List<InflationDTO>> getInflationByCountry(@PathVariable("country") String country) {
        return new ResponseEntity<>(inflationService.getInflationByCountry(country), HttpStatus.OK);
    }

    @ApiOperation(value = "List the inflation rates of a country in a year")
    @GetMapping("/get/{country}/{year}")
    public ResponseEntity<List<InflationDTO>> getInflationByCountryAndYear(@PathVariable("country") String country, @PathVariable("year") String year) {
        return new ResponseEntity<>(inflationService.getInflationByCountryAndYear(country, year), HttpStatus.OK);
    }
}
