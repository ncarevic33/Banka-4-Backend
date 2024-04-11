package rs.edu.raf.exchange.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.exchange.dto.ExchangeDTO;
import rs.edu.raf.exchange.service.ExchangeService;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@AllArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class ExchangeController {
    ExchangeService exchangeService;

    @ApiOperation(value = "Find exchange by name")
    @GetMapping("/findExchangeByName/{exchangeName}")
    public ResponseEntity<ExchangeDTO> getExchangeByName(@PathVariable("exchangeName") String name) {
        return new ResponseEntity<>(exchangeService.getExchangeByExchangeName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "List all exchanges")
    @GetMapping("/getAllExchanges")
    public ResponseEntity<List<ExchangeDTO>> listAllExchanges() {
        return new ResponseEntity<>(exchangeService.getAllExchanges(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all exchanges that use this currency")
    @GetMapping("/findExchangesByCurrency/{currency}")
    public ResponseEntity<List<ExchangeDTO>> getExchangesByCurrency(@PathVariable("currency") String currency) {
        return new ResponseEntity<>(exchangeService.getExchangesByCurrency(currency), HttpStatus.OK);
    }


    @ApiOperation(value = "Find all exchanges that are open")
    @GetMapping("/findOpenExchanges")
    public ResponseEntity<List<ExchangeDTO>> listOpenExchanges() {
        return new ResponseEntity<>(exchangeService.getOpenExchanges(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all exchanges that have this polity")
    @GetMapping("/findExchangesByPolity/{polity}")
    public ResponseEntity<List<ExchangeDTO>> getExchangesByPolity(@PathVariable("polity") String polity) {
        return new ResponseEntity<>(exchangeService.getExchangesByPolity(polity), HttpStatus.OK);
    }
}
