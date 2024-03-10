package rs.edu.raf.transakcija.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.transakcija.dto.*;
import rs.edu.raf.transakcija.mapper.DtoOriginalMapper;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.servis.OneTimePasswService;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    public TransakcijaServis transakcijaServis;
    public OneTimePasswService oneTimePasswTokenService;
    public DtoOriginalMapper dtoOriginalMapper;

    //AUTOMATSKI CONSTRUCTOR INJECT IMPLEMENTACIJE
    //AKO IMA VISE IMPLEMENTACIJA,MORA SE NAVESTI KONKRETNA PREKO QUALIFIER
    @Autowired
    public TransactionController(TransakcijaServis transakcijaServis,
                                 OneTimePasswService oneTimePasswTokenService,
                                 DtoOriginalMapper dtoOriginalMapper) {
        this.transakcijaServis = transakcijaServis;
        this.oneTimePasswTokenService = oneTimePasswTokenService;
        this.dtoOriginalMapper = dtoOriginalMapper;
    }


    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="GENERISANJE JEDNOKRATNE LOZINKE",description = "")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracena jednokratna lozinka"),
    })
    @GetMapping(value = "/oneTimePassw",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getGeneratedOneTimePass(){

        return new ResponseEntity<>(oneTimePasswTokenService.generateOneTimePassw(),HttpStatus.OK);

    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="PROVERA ISPRAVNOSTI UPLATA TRANSAKCIJE SA JEDNOKRATNOM LOZINKOM",description = "proverena ispravnost uplata transakcije")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesna provera ispravnosti"),
    })
    @GetMapping(value = "/transactionUplataAndPassw",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> proveraIspravnostiUplataTransaction(@RequestBody UplataTransactionAndOneTimePasswDTO payTransactionAndOneTimePasswDTO){


        Uplata uplata = (Uplata) dtoOriginalMapper.newDtoToNewOriginal(payTransactionAndOneTimePasswDTO.getUplataDTO());


        boolean ispravnost = transakcijaServis.proveraIspravnostiUplataTransakcije(uplata);

      if(ispravnost)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="PROVERA ISPRAVNOSTI PRENOS SREDSTAVA TRANSAKCIJE SA JEDNOKRATNOM LOZINKOM",description = "proverena ispravnost prenos sredstava transakcije")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesna provera ispravnosti"),
    })
    @GetMapping(value = "/transactionPrenosSredstavaAndPassw",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> proveraIspravnostiPrenosSredstavaTransaction(@RequestBody PrenosSredstavaTransactionAndOneTimePasswDTO transferTransactionAndOneTimePasswDTO){

        PrenosSredstava prenosSredstava = (PrenosSredstava) dtoOriginalMapper.newDtoToNewOriginal(transferTransactionAndOneTimePasswDTO.getPrenosSredstavaDTO());

        boolean ispravnost = transakcijaServis.proveraIspravnostiPrenosSredstavaTransakcije(prenosSredstava);

        if(ispravnost)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
    /////////////////////////////////////////////////////////////////////////////////////////

    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE TRANSAKCIJA PREKO ID KORISNIKA",description = "prosledjuje se  u path param id korisnika cije transakcije trebaju da se vrate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracene transakcije korisnika"),
    })
    @GetMapping(value = "/getAllTransactionsByKorisnikId/{korisnikId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> getAllTransactionsByKorisnikId(@PathVariable("korisnikId") Long clientId) {

        List<Object> transactions = transakcijaServis.getAllTransactionsByKorisnikId(clientId);

        if(transactions != null)
            return new ResponseEntity<>((List<Object>) dtoOriginalMapper.originalToDtoWithId(transactions), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE SVIH UPLATA TRANSAKCIJA PO BROJU RACUNA",description = "prosledjuje se u path param id racuna cije transakcije trebaju da se vrate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracene transakcije racuna"),
    })
    @GetMapping(value = "/getAllUplateByBrojRacuna/{brojRacuna}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UplataDTO>> getAllUplateByBrojRacuna(@PathVariable("brojRacuna") Long billId) {

        List<UplataDTO> uplateKaILIOdKorisnika = new ArrayList<>();

        UplataDTO uplataDTO1 = transakcijaServis.dobaciUplatuSretstavaDTOPoBrojuPrimaoca(billId);
        UplataDTO uplataDTO2 = transakcijaServis.dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(billId);

        if(uplataDTO1 != null && uplataDTO2 != null) {
          //SAMO 2 UPLATE JER METODE SERVISA NE VRACAJU LISTU
            uplateKaILIOdKorisnika.add(uplataDTO1);
            uplateKaILIOdKorisnika.add(uplataDTO2);

            return new ResponseEntity<>(uplateKaILIOdKorisnika,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE SVIH PRENOS SREDSTAVA TRANSAKCIJA PO BROJU RACUNA",description = "prosledjuje se u path param id racuna cije transakcije trebaju da se vrate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracene transakcije racuna"),
    })

    @GetMapping(value = "/getAllPrenosSredstavaByBrojRacuna/{brojRacuna}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrenosSredstavaDTO>> getAllPrenosSredstavaByBrojRacuna(@PathVariable("brojRacuna") Long billId) {

        List<PrenosSredstavaDTO> prenosiSredstavaKaILIOdKorisnika = new ArrayList<>();

        PrenosSredstavaDTO prenosSredstavaDTO1 = transakcijaServis.dobaviPrenosSretstavaDTOPoBrojuPrimaoca(billId);
        PrenosSredstavaDTO prenosSredstavaDTO2 = transakcijaServis.dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(billId);

        if(prenosSredstavaDTO1 != null && prenosSredstavaDTO2 != null) {
            //SAMO 2 PRENOSTA SREDSTAVA JER METODE SERVISA NE VRACAJU LISTU
            prenosiSredstavaKaILIOdKorisnika.add(prenosSredstavaDTO1);
            prenosiSredstavaKaILIOdKorisnika.add(prenosSredstavaDTO2);

            return new ResponseEntity<>(prenosiSredstavaKaILIOdKorisnika, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE UPLATE PREKO ID UPLATE",description = "prosledjuje se u path id uplate koja treba da se vrati")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracena uplata"),
    })
    @GetMapping(value = "/getUplataTransactionByTransactionId/{uplataId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UplataDTO> getUplataTransactionById(@PathVariable("uplataId") Long transactionId) {

        UplataDTO uplataDTO = transakcijaServis.dobaciUplatuSretstavaDTOPoID(transactionId);
        if(uplataDTO != null)
            return new ResponseEntity<>(uplataDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE PRENOSA SREDSTAVA PREKO ID PRENOSA SREDSTAVA",description = "prosledjuje se u path id prenosa sredstava koji treba da se vrati")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vracen prenos sredstava"),
    })
    @GetMapping(value = "/getPrenosSredstavaTransactionByTransactionId/{prenosSredstavaId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrenosSredstavaDTO> getPrenosSredstavaTransactionById(@PathVariable("prenosSredstavaId") Long transactionId) {

        PrenosSredstavaDTO prenosSredstavaDTO = transakcijaServis.dobaviPrenosSretstavaDTOPoID(transactionId);

        if(prenosSredstavaDTO != null)
            return new ResponseEntity<>(prenosSredstavaDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DOHVATANJE SACUVANIH SABLONA TRANSAKCIJE",description = "")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno vraceni sabloni transakcije"),
    })
    @GetMapping(value = "/getSavedTransactionalPatterns",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SablonTransakcijeDTO>> getSavedTransactionalPatterns() {

        return new ResponseEntity<>((List<SablonTransakcijeDTO>) dtoOriginalMapper.originalToDtoWithId(transakcijaServis.getSavedTransactionalPatterns()), HttpStatus.OK);

    }
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="DODAVANJE NOVOG SABLONA TRANSAKCIJE",description = "prosledjuje se u body sablon transakcije koji treba da se doda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno dodat sablon transakcije"),
    })

    @PostMapping(value = "/addNewTransactionalPattern",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addNewTransactionalPattern(@RequestBody NoviSablonTransakcijeDTO noviSablonTransakcijeDTO) {

        SablonTransakcije sablonTransakcije = transakcijaServis.addNewTransactionalPattern((SablonTransakcije) dtoOriginalMapper.newDtoToNewOriginal(noviSablonTransakcijeDTO));

        if(sablonTransakcije != null)
            return ResponseEntity.status(HttpStatus.OK).body("Operacija dodavanja sablona transakcije je uspesno izvrsena");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Operacija dodavanja sablona nije uspela");
    }
    /////////////////////////////////////////////////////////////////////////
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="BRISANJE JEDNOG TRANSAKCIONOG SABLONA",description = "prosledjuje se id transakcije ciji sablon treba da se obrise")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno obrisan transakcioni sablon"),
    })
    @DeleteMapping(value = "/deleteTransactionalPattern/{transactionPatternId}",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteTransactionalPattern(@PathVariable("transactionPatternId") Long transactionPatternId) {

        if(transakcijaServis.deleteTransactionalPattern(transactionPatternId))
            return new ResponseEntity<>("Operacija brisanja transakcionog sablona je uspesno izvrsena",HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body("Operacija brisanja transakcije je uspesno izvrsena");
        return new ResponseEntity<>("Operacija brisanja transakcionog sablona nije uspesno izvrsena",HttpStatus.NOT_ACCEPTABLE);
    }
    @Tag(name = "TRANSAKCIJE", description = "Transakcija API")
    @Operation(summary ="BRISANJE SVIH TRANSAKCIONIH SABLONA",description = "")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "uspesno obrisani svi sabloni transakcije"),
    })
    @DeleteMapping(value = "/deleteAllTransactionalPatterns",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteAllTransactionalPatterns() {

        transakcijaServis.deleteAllTransactionalPatterns();

        return new ResponseEntity<>("Operacija brisanja svih transakcionih sablona je uspesno izvrsena",HttpStatus.OK);
        //return ResponseEntity.ok("Operacija brisanja svih transakcija je uspesno izvrsena");

    }

}
