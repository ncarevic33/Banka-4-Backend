package rs.edu.raf.korisnik.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.korisnik.dto.IzmenaRadnikaDTO;
import rs.edu.raf.korisnik.dto.NoviRadnikDTO;
import rs.edu.raf.korisnik.dto.RadnikDTO;
import rs.edu.raf.korisnik.servis.KorisnikServis;

import java.util.List;

@RestController
@RequestMapping("/radnik")
@AllArgsConstructor
@SecurityRequirement(name="jwt")
@CrossOrigin(origins = "*")
public class RadnikController {
    private KorisnikServis korisnikServis;

    @PostMapping
    @Operation(description = "Dodaj novog radnika")
    public ResponseEntity<RadnikDTO> dodajRadnika(@Parameter(description = "Podaci o radniku") NoviRadnikDTO noviRadnikDTO) {
        return new ResponseEntity<>(korisnikServis.kreirajNovogRadnika(noviRadnikDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(description = "Izmeni postojeceg radnika")
    public ResponseEntity<RadnikDTO> izmeniRadnika(@Parameter(description = "Novi podaci o radniku") IzmenaRadnikaDTO izmenaRadnikaDTO) {
        return new ResponseEntity<>(korisnikServis.izmeniRadnika(izmenaRadnikaDTO),HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Svi radnici")
    public ResponseEntity<List<RadnikDTO>> sviAktivniRadnici() {
        return new ResponseEntity<>(korisnikServis.izlistajSveAktivneRadnike(),HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @Operation(description = "Podaci o radniku koji ima email")
    public ResponseEntity<RadnikDTO> nadjiRadnikaPoEmail(@PathVariable("email") @Parameter(description = "email radnika") String email) {
        return new ResponseEntity<>(korisnikServis.nadjiAktivnogRadnikaPoEmail(email),HttpStatus.OK);
    }


}

