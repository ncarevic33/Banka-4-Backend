package rs.edu.raf.transakcija.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.edu.raf.transakcija.dto.*;
import rs.edu.raf.transakcija.mapper.DtoOriginalMapper;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.servis.OneTimePasswService;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {


    private TransactionController transactionControllerTestMyMethods;

    @Before
    public void setUp() {
        this.transactionControllerTestMyMethods = new TransactionController(
                mock(TransakcijaServis.class),
                mock(OneTimePasswService.class),
                mock(DtoOriginalMapper.class));
                //ILI KAO POLJA
                //@Mock TransakcijaServis transakcijaServis
                //@Mock OneTimePasswTokenService oneTimePasswTokenService
                //@Mock DtoOriginalMapper dtoOriginalMapper
                //@InjectMocks TransactionController transactionController

    }

    @Test
    public void testGetGeneratedOneTimePassTESTgenerateOneTimePassw(){

        try {
            assertEquals(new ResponseEntity<String>(HttpStatus.OK),transactionControllerTestMyMethods.getGeneratedOneTimePass());
        }catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testTransactionUplataTESTProveraIspravnostiUplataTransakcije() {

         UplataTransactionAndOneTimePasswDTO uplataTransactionAndOneTimePasswDTO = new UplataTransactionAndOneTimePasswDTO();


        Uplata uplata = new Uplata();


        when(transactionControllerTestMyMethods.dtoOriginalMapper.newDtoToNewOriginal(uplataTransactionAndOneTimePasswDTO))
                .thenReturn(uplata);


        try {
            ResponseEntity<Void> response = transactionControllerTestMyMethods.proveraIspravnostiUplataTransaction(uplataTransactionAndOneTimePasswDTO);

            assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE),response);
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testTransactionPrenosSredstavaTESTproveraIspravnostiPrenosSredstavaTransakcije(){

        PrenosSredstavaTransactionAndOneTimePasswDTO transferTransactionAndOneTimePasswDTO = new PrenosSredstavaTransactionAndOneTimePasswDTO();
        PrenosSredstava prenosSredstava =  new PrenosSredstava();

        when(transactionControllerTestMyMethods.dtoOriginalMapper.newDtoToNewOriginal(transferTransactionAndOneTimePasswDTO)).thenReturn(prenosSredstava);

        try {
            assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.proveraIspravnostiPrenosSredstavaTransaction(transferTransactionAndOneTimePasswDTO));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetAllTransactionsByKorisnikIdTESToriginalToDtoWithId() {

        Long korisnikId = Long.valueOf(1);
        List<Object> transactions = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getAllTransactionsByKorisnikId(korisnikId)).thenReturn(transactions);

        try {
            assertEquals(new ResponseEntity<List<Object>>(HttpStatus.OK),transactionControllerTestMyMethods.getAllTransactionsByKorisnikId(korisnikId));
        }catch (Exception e) {
            fail(e.getMessage());
        } }

    @Test
    public void testGetUplataTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        Uplata uplata = new Uplata();

        when(transactionControllerTestMyMethods.transakcijaServis.nadjiUplatuPoId(transactionId)).thenReturn(uplata);

        try {
            assertEquals(new ResponseEntity<UplataDTO>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.getUplataTransactionById(transactionId));
        }catch (Exception e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testGetPrenosSredstavaTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        PrenosSredstava prenosSredstava = new PrenosSredstava();

        when(transactionControllerTestMyMethods.transakcijaServis.nadjiPrenosSretstavaPoId(transactionId)).thenReturn(prenosSredstava);

        try {
            assertEquals(new ResponseEntity<PrenosSredstavaDTO>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.getPrenosSredstavaTransactionById(transactionId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testGetSavedPatternsTESToriginalToDtoWithId() {

        List<SablonTransakcije> sabloniTransakcije = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getSavedTransactionalPatterns()).thenReturn(sabloniTransakcije);

        try {
            assertEquals(new ResponseEntity<List<rs.edu.raf.transakcija.dto.SablonTransakcijeDTO>>(HttpStatus.OK),transactionControllerTestMyMethods.getSavedTransactionalPatterns());
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddNewTransactionalPatternTESTaddNewTransactionalPattern() {

        NoviSablonTransakcijeDTO noviSablonTransakcijeDTO = new NoviSablonTransakcijeDTO();
        SablonTransakcije sablonTransakcije = new SablonTransakcije();

        when(transactionControllerTestMyMethods.dtoOriginalMapper.newDtoToNewOriginal(noviSablonTransakcijeDTO)).thenReturn(sablonTransakcije);

        try {
            assertEquals(new ResponseEntity<String>("Operacija dodavanja sablona nije uspela",HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.addNewTransactionalPattern(noviSablonTransakcijeDTO));
        }catch (Exception e) {
            fail(e.getMessage());
        }

    }
    @Test
    public void testDeleteTransactionalPatternTESTdeleteTransactionalPattern() {

        Long transactionalId = Long.valueOf(1);

        try {
            assertEquals(new ResponseEntity<String>("Operacija brisanja transakcionog sablona nije uspesno izvrsena",HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.deleteTransactionalPattern(transactionalId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteAllTransactionalPatternsTESTdeleteAllTransactionalPatterns() {

        try {
            assertEquals(new ResponseEntity<String>("Operacija brisanja svih transakcionih sablona je uspesno izvrsena",HttpStatus.OK),transactionControllerTestMyMethods.deleteAllTransactionalPatterns());
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }


}
