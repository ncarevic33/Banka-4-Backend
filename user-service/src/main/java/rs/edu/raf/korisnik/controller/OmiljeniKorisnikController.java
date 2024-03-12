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

    @ApiOperation(value = "Dohvati Omiljenog Korisnika po id")
    @GetMapping("/{id}")
    public ResponseEntity<OmiljeniKorisnikDTO> dohvatiOmiljenogKorisnika(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(omiljeniKorisnikServis.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Obrisi Omiljenog Korisnika po id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> obrisiOmiljenogKorisnika(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        omiljeniKorisnikServis.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}