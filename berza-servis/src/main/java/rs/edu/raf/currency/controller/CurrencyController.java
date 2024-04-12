package rs.edu.raf.currency.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.currency.dto.CurrencyDTO;
import rs.edu.raf.currency.servis.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @ApiOperation(value = "Find currency by currency code")
    @GetMapping("/get/{currencyCode}")
    public ResponseEntity<CurrencyDTO> getCurrency(@PathVariable("currencyCode") String code) {
        return new ResponseEntity<>(currencyService.getCurrencyByCurrencyCode(code), HttpStatus.OK);
    }

    @ApiOperation(value = "List all currencies")
    @GetMapping("/get")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

    @ApiOperation(value = "List all currencies used in a polity")
    @GetMapping("/get/polity/{polity}")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrenciesByPolity(@PathVariable("polity") String polity) {
        return new ResponseEntity<>(currencyService.getCurrenciesInPolity(polity), HttpStatus.OK);
    }
}
