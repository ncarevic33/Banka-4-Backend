package rs.edu.raf.racun.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.racun.dto.*;
import rs.edu.raf.racun.model.DevizniRacun;
import rs.edu.raf.racun.model.Firma;
import rs.edu.raf.racun.model.PravniRacun;
import rs.edu.raf.racun.model.TekuciRacun;
import rs.edu.raf.racun.servis.RacunServis;

import java.util.List;

@RestController
@RequestMapping("/racuni")
@Tag(name = "Racuni", description = "Operacije nad racunima")
@AllArgsConstructor
public class RacunController {

    private RacunServis racunServis;


    @ApiOperation(value = "Dodaj devizni racun")
    @PostMapping("/dodajDevizni")
    public ResponseEntity<DevizniRacun> dodajDevizniRacun(@RequestHeader("Authorization") String authorization, @RequestBody NoviDevizniRacunDTO noviDevizniRacunDTO){
        return new ResponseEntity<>(racunServis.kreirajDevizniRacun(noviDevizniRacunDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Dodaj pravni racun")
    @PostMapping("/dodajPravni")
    public ResponseEntity<PravniRacun> dodajPravniRacun(@RequestHeader("Authorization") String authorization, @RequestBody NoviPravniRacunDTO noviPravniRacunDTO){
        return new ResponseEntity<>(racunServis.kreirajPravniRacun(noviPravniRacunDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Dodaj tekuci racun")
    @PostMapping("/dodajTekuci")
    public ResponseEntity<TekuciRacun> dodajTekuciRacun(@RequestHeader("Authorization") String authorization, @RequestBody NoviTekuciRacunDTO noviTekuciRacunDTO){
        return new ResponseEntity<>(racunServis.kreirajTekuciRacun(noviTekuciRacunDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "Izlistaj racune korisnika")
    @GetMapping("/nadjiRacuneKorisnika/{idKorisnik}")
    public ResponseEntity<List<RacunDTO>> izlistajRacuneKorisnika(@RequestHeader("Authorization") String authorization,@PathVariable("idKorisnik") Long idKorisnik){
        return new ResponseEntity<>(racunServis.izlistavanjeRacunaJednogKorisnika(idKorisnik), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji racun")
    @GetMapping("/nadjiRacun/{id}")
    public ResponseEntity<RacunDTO> nadjiRacunPoId(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){
        return new ResponseEntity<>(racunServis.nadjiAktivanRacunPoID(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji devizni racun")
    @GetMapping("/nadjiDevizniRacun/{id}")
    public ResponseEntity<DevizniRacun> nadjiDevizniRacunPoId(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){
        return new ResponseEntity<>(racunServis.nadjiAktivanDevizniRacunPoID(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji pravni racun")
    @GetMapping("/nadjiPravniRacun/{id}")
    public ResponseEntity<PravniRacun> nadjiPravniRacunPoId(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){
        return new ResponseEntity<>(racunServis.nadjiAktivanPravniRacunPoID(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji tekuci racun")
    @GetMapping("/nadjiTekuciRacun/{id}")
    public ResponseEntity<TekuciRacun> nadjiTekuciRacunPoId(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){
        return new ResponseEntity<>(racunServis.nadjiAktivanTekuciRacunPoID(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji racun po broju racuna")
    @GetMapping("/nadjiRacunPoBroju/{brojRacuna}")
    public ResponseEntity<RacunDTO> nadjiRacunPoBroju(@RequestHeader("Authorization") String authorization,@PathVariable("brojRacuna") Long brojRacuna){
        return new ResponseEntity<>(racunServis.nadjiAktivanRacunPoBrojuRacuna(brojRacuna), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji devizni racun po broju racuna")
    @GetMapping("/nadjiDevizniRacunPoBroju/{brojRacuna}")
    public ResponseEntity<DevizniRacun> nadjiDevizniRacunPoBroju(@RequestHeader("Authorization") String authorization,@PathVariable("brojRacuna") Long brojRacuna){
        return new ResponseEntity<>(racunServis.nadjiAktivanDevizniRacunPoBrojuRacuna(brojRacuna), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji pravni racun po broju racuna")
    @GetMapping("/nadjiPravniRacunPoBroju/{brojRacuna}")
    public ResponseEntity<PravniRacun> nadjiPravniRacunPoBroju(@RequestHeader("Authorization") String authorization,@PathVariable("brojRacuna") Long brojRacuna){
        return new ResponseEntity<>(racunServis.nadjiAktivanPravniRacunPoBrojuRacuna(brojRacuna), HttpStatus.OK);
    }
    @ApiOperation(value = "Nadji tekuci racun po broju racuna")
    @GetMapping("/nadjiTekuciRacunPoBroju/{brojRacuna}")
    public ResponseEntity<TekuciRacun> nadjiTekuciRacunPoBroju(@RequestHeader("Authorization") String authorization,@PathVariable("brojRacuna") Long brojRacuna){
        return new ResponseEntity<>(racunServis.nadjiAktivanTekuciRacunPoBrojuRacuna(brojRacuna), HttpStatus.OK);
    }
    @ApiOperation(value = "Izlistaj sve firme")
    @GetMapping("/izlistajSveFirme")
    public ResponseEntity<List<FirmaDTO>> izlistajSveFirme(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(racunServis.izlistajSveFirme(), HttpStatus.OK);
    }
    @ApiOperation(value = "Kreiraj novu firmu")
    @PostMapping("/kreirajFirmu")
    public ResponseEntity<Firma> dodajDevizniRacun(@RequestHeader("Authorization") String authorization, @RequestBody NovaFirmaDTO novaFirmaDTO){
        return new ResponseEntity<>(racunServis.kreirajFirmu(novaFirmaDTO), HttpStatus.OK);
    }





}
