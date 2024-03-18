package rs.edu.raf.berza.opcija.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.servis.OpcijaServis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/opcija")
public class OpcijaController {

    @Autowired
    private OpcijaServis opcijaServis;

    @GetMapping("")
    @Operation(description = "Kreiraj opciju")
    public ResponseEntity<OpcijaDto> kreirajOpciju(@RequestBody NovaOpcijaDto novaOpcijaDto) {
       return new ResponseEntity<>(opcijaServis.save(novaOpcijaDto),HttpStatus.OK);

    }
    @GetMapping("all")
    public ResponseEntity<List<OpcijaDto>> findAll() throws InterruptedException {
        return new ResponseEntity<>(opcijaServis.findAll(),HttpStatus.OK);

    }

    @PostMapping("/izvrsi-opciju/{opcijaId}")
    @Operation(description = "Izvrsi put ili call opciju")
    public ResponseEntity<String> izvrsiOpciju(@PathVariable("opcijaId") Long opcijaId){
        opcijaServis.izvrsiOpciju(opcijaId);
        return new ResponseEntity<>("Operacija izvrsena",HttpStatus.OK);

    }

    @GetMapping("/stanje-opcije/{opcijaId}")
    @Operation(description = "Proveri stanje opcije")
    public ResponseEntity<OpcijaDto> stanjeOpcije(@PathVariable("opcijaId") Long opcijaId){
        return new ResponseEntity<>(opcijaServis.proveriStanjeOpcije(opcijaId),HttpStatus.OK);
    }


    //query parametri
    @GetMapping(value = "/filtrirajOpcije", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OpcijaDto>> filtrirajOpcije(@RequestParam String ticker,
                                                           @RequestParam LocalDateTime datumIsteka,
                                                           @RequestParam double strikePrice
                                                        ){

        return new ResponseEntity<>(this.opcijaServis.findByStockAndDateAndStrike(ticker,datumIsteka,strikePrice), HttpStatus.OK);
    }
    /////////////////////////////////////////////////////////////////////////



}
