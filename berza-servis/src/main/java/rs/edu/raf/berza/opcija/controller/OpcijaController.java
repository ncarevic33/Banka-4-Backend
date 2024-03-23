package rs.edu.raf.berza.opcija.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.berza.opcija.dto.NovaOpcijaDto;
import rs.edu.raf.berza.opcija.dto.OpcijaDto;
import rs.edu.raf.berza.opcija.model.OpcijaStanje;
import rs.edu.raf.berza.opcija.servis.OpcijaServis;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/opcija")
public class OpcijaController {

    @Autowired
    private OpcijaServis opcijaServis;


    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", description = "")
    })*/

    @PostMapping("kreiraj-opciju")
    @Operation(description = "Kreiraj opciju")
    public ResponseEntity<OpcijaDto> kreirajOpciju(@RequestBody NovaOpcijaDto novaOpcijaDto) {
       return new ResponseEntity<>(opcijaServis.save(novaOpcijaDto),HttpStatus.OK);

    }

    @GetMapping("sve-opcije")
    @Operation(description = "uzmi sve opcije")
    public ResponseEntity<List<OpcijaDto>> findAll() throws InterruptedException {
        return new ResponseEntity<>(opcijaServis.findAll(),HttpStatus.OK);

    }


    @PostMapping("/izvrsi-opciju/{opcijaId}/{userId}")
    @Operation(description = "Izvrsi put ili call opciju")
    public ResponseEntity<String> izvrsiOpciju(@PathVariable("opcijaId") Long opcijaId,@PathVariable("userId") Long userId){
        opcijaServis.izvrsiOpciju(opcijaId,userId);
        return new ResponseEntity<>("Operacija izvrsena",HttpStatus.OK);

    }

    @GetMapping("/stanje-opcije/{opcijaId}")
    @Operation(description = "Proveri stanje opcije")
    public ResponseEntity<OpcijaStanje> stanjeOpcije(@PathVariable("opcijaId") Long opcijaId){
        return new ResponseEntity<>(opcijaServis.proveriStanjeOpcije(opcijaId),HttpStatus.OK);
    }


    //query parametri
    @Operation(description = "Filtriraj opcije")
    @GetMapping(value = "/filtriraj-opcije", produces = MediaType.APPLICATION_JSON_VALUE)
                                                        //parametri se moraju zvati ovako ako su prosledjeni ali ne moraju biti svi prosledjeni onda ce biti null
    public ResponseEntity<List<OpcijaDto>> filtrirajOpcije(@RequestParam(name = "ticker",required = false) String ticker,
                                                           @RequestParam(name = "datumIsteka",required = false) String datumIsteka,//u milisec
                                                           @RequestParam(name = "strikePrice",required = false) Double strikePrice
                                                        ){
        LocalDateTime localDate = null;
        if(datumIsteka != null)
        localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(datumIsteka)),ZoneId.systemDefault()); // Konvertovanje u LocalDate

        return new ResponseEntity<>(this.opcijaServis.findByStockAndDateAndStrike(ticker,localDate,strikePrice), HttpStatus.OK);
    }
    /////////////////////////////////////////////////////////////////////////



}
