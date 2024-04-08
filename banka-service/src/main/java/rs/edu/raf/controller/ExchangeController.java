package rs.edu.raf.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.model.dto.ExchangeRateResponseDto;
import rs.edu.raf.service.ExchangeRateService;

import java.util.List;

@RestController
@RequestMapping("/exchange")
//@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
@Tag(name = "Menjacnica", description = "Funkcionalnosti koje omogucava menjacnica")
public class ExchangeController {


    private final ExchangeRateService exchangeRateService;


    @Autowired
    public ExchangeController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @ApiOperation(value = "Vraca kursnu listu. Dinar je glavna valuta (RSD).")
    @GetMapping
    public ResponseEntity<List<ExchangeRateResponseDto>> getAllCurrencyRates(@RequestAttribute("userId") Long userId){
//        public ResponseEntity<List<ExchangeRateResponseDto>> getAllCurrencyRates(@RequestAttribute("userId") Long userId){
        //System.out.println("userId iz kontrolera: " + userId);
        return new ResponseEntity<>(exchangeRateService.getAllExchangeRates(), HttpStatus.OK) ;
    }

}
