package rs.edu.raf.korisnik.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.korisnik.dto.OmiljeniKorisnikDTO;
import rs.edu.raf.korisnik.servis.OmiljeniKorisnikServis;

@RestController
@RequestMapping("/omiljeni-korisnik")
@Tag(name = "OmiljeniKorisnik", description = "Operacije nad Omiljenim Korisnicima")
@AllArgsConstructor
public class OmiljeniKorisnikController {

    private OmiljeniKorisnikServis omiljeniKorisnikServis;


    @ApiOperation(value = "Dodaj Omiljenog Korisnika")
    @PostMapping("")
    public ResponseEntity<OmiljeniKorisnikDTO> dodajOmiljenogKorisnika(@RequestHeader("Authorization") String authorization, @RequestBody OmiljeniKorisnikDTO omiljeniKorisnikDTO){
        return new ResponseEntity<>(omiljeniKorisnikServis.add(omiljeniKorisnikDTO), HttpStatus.OK);
    }

//    @ApiOperation(value = "Izlistaj racune korisnika")
//    @GetMapping("/{idKorisnik}")
//    public ResponseEntity<List<RacunDTO>> izlistajRacuneKorisnika(@RequestHeader("Authorization") String authorization,@PathVariable("idKorisnik") Long idKorisnik){
//        return new ResponseEntity<>(racunServis.izlistavanjeRacunaJednogKorisnika(idKorisnik), HttpStatus.OK);
//    }



}
