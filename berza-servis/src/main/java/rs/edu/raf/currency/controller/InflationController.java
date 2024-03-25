package rs.edu.raf.currency.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    private InflationService inflationService;

    @ApiOperation(value = "List the inflation rates of a currency")
    @GetMapping("/get/{currencyCode}")
    public ResponseEntity<List<InflationDTO>> getInflationByCurrencyCode(@PathVariable("currencyCode") String currencyCode) {
        return new ResponseEntity<>(inflationService.getInflationByCurrencyCode(currencyCode), HttpStatus.OK);
    }

    @ApiOperation(value = "List the inflation rates of a currency in a year")
    @GetMapping("/get/{currencyCode}/{year}")
    public ResponseEntity<List<InflationDTO>> getInflationByCurrencyCodeAndYear(@PathVariable("currencyCode") String currencyCode, @PathVariable("year") String year) {
        return new ResponseEntity<>(inflationService.getInflationByCurrencyCodeAndYear(currencyCode, year), HttpStatus.OK);
    }
}
