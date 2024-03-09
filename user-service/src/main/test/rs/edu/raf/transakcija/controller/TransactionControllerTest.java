package rs.edu.raf.transakcija.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.edu.raf.transakcija.dto.*;
import rs.edu.raf.transakcija.mapper.DtoOriginalMapper;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.servis.OneTimePasswTokenService;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.ArrayList;
import java.util.List;


public class TransactionControllerTest {


    //KORISTE SE TESTNI NAMESTENI PREDEFINISANI PODACI(INPUT) ILI NAMESTENI PODACI U METODAMA MOCKOVA(INPUT I OUTPUT)
    //ODNOSNO PREDEFINISANI PODACI SU POCETNI INPUT1 ZA METODU TESTNE KLASE A MOCKOVI SU LANAC INPUTA I OUTPUTA KOJI KRECE OD INPUT1

    //TESTNA KLASA SA METODOM ZA TESTIRANJE
    private TransactionController transactionControllerTestMyMethods;

    //IZVRSAVA SE PRE SVAKE @TEST ANOTACIJE
    //ODNOSNO ZA SVAKI POJEDINACNI TEST PRAVIMO NOVI TESTNI OBJEKAT DA BI REZULTATI BILI NEZAVISNI
    @Before
    public void setUp() {
        this.transactionControllerTestMyMethods = new TransactionController(
                //OBJEKTI OD KOJIH ZAVISI TESTNA KLASA AKO NIJE NJENO TESTIRANJE
                //ALI TOKOM TESTA TESTNA KLASA NE ZAVISI OD NJIH
                mock(TransakcijaServis.class),
                mock(OneTimePasswTokenService.class),
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


    //3 BITNE FUNKCIJE
    //testTransactionUplataTESTProveraIspravnostiUplataTransakcije
    //transactionUplata
    //proveraIspravnostiUplataTransakcije
    //TESTIRAMO JEDNU A ZAPRAVO SMO TESTIRALI 2 FUNKCIJE ODNOSNO WRAPPER I PRAVU POSLEDNJU KOMPOZITNU
    //TESTIRAMO transactionUplata A ZAPRAVO SMO TESTIRALI I proveraIspravnostiUplataTransakcije
    //SVE OSTALE FUNKCIJE U transactionUplata POSMATRAMO DA IDEALNO RADE DOBRO

    //TESTNA FUNKCIJA JE UVEK OBLIKA WRAPPERA ZA ULANCANE FUNKCIJE U KOJIMA SE ZAPRAVO OUTPUT POSLEDNJE TESTIRA ZA PRETHODNO ULANCANE PREDEFINISANE INPUTE I OUTPUTE
    //DOBIJE NEKI INPUT, ZOVE VISE METODA NAD NEKIM OBJEKTIMA ULANCANIM REDOM POCEV OD TOG PRVO INPUTA A NA DALJE SE SMENJUJU INPUT I OUTPUT DO POSLEDNJEG OUTPUTA
    //VRACA POSLEDNJI OUTPUT U NIZU ULANCANIH FUNKCIJA

    //TESTIRAS METODU SAMO PO 1 PREDEFINISANOM INPUTU ODNOSNO SVAKI NOVI PREDEFINISANI INPUT JE NOVI TEST TE METODE
    //ZAPRAVO PREDEFINISES KAO PODATAK 1 INPUT TESTNE METODE I SVE OUTPUTE KOMPOZITNIH METODA OSIM POSLEDNJEG OUTPUTA JER GA TESTIRAS!!!!!!!!!!!!
    @Test
    public void testTransactionUplataTESTProveraIspravnostiUplataTransakcije() {


        //INPUT POCETNI PODATAK ZA METODE KOJU TESTIRAMO IZ TESTNE KLASE

        //INPUT TESTNE METODE ZA KOJI PRETPOSTAVLJAMO DA JE TACAN
        //ODNOSNO OCEKIVACEMO DA TEST PRODJE ODNOSNO DA JE assertEquals TRUE
        UplataTransactionAndOneTimePasswDTO uplataTransactionAndOneTimePasswDTO = new UplataTransactionAndOneTimePasswDTO();

        //OUTPUT PRVE KOMPOZITNE METODE
        Uplata uplata = new Uplata();


        /////////////////////////////////
       //when .thenReturn KORISTIS ZA METODE KOJE ZOVE TESTNA METODA TAKO DA VRATE NAMESTENI PODATAK
        when(transactionControllerTestMyMethods.dtoOriginalMapper.newDtoToNewOriginal(uplataTransactionAndOneTimePasswDTO))
                .thenReturn(uplata);

        //TESTNI REZULTAT OD ZAPRAVO PRAVE TESTIRANE METODE proveraIspravnostiUplataTransakcije


        //PROVERA OUTPUTA POSLEDNJE KOMPOZITNE METODE KOJU ZAPRAVO TESTIRAMO
        //OUTPUT JE ILI EXCEPTION ILI REZULTAT KOJI OCEKUJEMO

        try {
            ResponseEntity<Void> response = transactionControllerTestMyMethods.transactionUplata(uplataTransactionAndOneTimePasswDTO);

            //PRETPOSTAVLJENI OUTPUT PRAVE TESTIRANE METODE proveraIspravnostiUplataTransakcije ZA PREDEFINISANI PROSLEDJENI INPUT U transactionUplata
            //PRETPOSTAVLJENI OUTPUT NE SME BITI RANDOM ZA ISTI INPUT VEC KONACNI
            //SAMO 1 assertEquals IMAS UVEK
            assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE),response);
        }catch (Exception e) {
            //AKO TESTNA METODA BACI EXCEPTION A TO UVEK MOZE
            fail(e.getMessage());
        }
    }

    @Test
    public void testTransactionPrenosSredstavaTESTproveraIspravnostiPrenosSredstavaTransakcije(){

        PrenosSredstavaTransactionAndOneTimePasswDTO transferTransactionAndOneTimePasswDTO = new PrenosSredstavaTransactionAndOneTimePasswDTO();
        PrenosSredstava prenosSredstava =  new PrenosSredstava();

        when(transactionControllerTestMyMethods.dtoOriginalMapper.newDtoToNewOriginal(transferTransactionAndOneTimePasswDTO)).thenReturn(prenosSredstava);

        try {
            assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.transactionPrenosSredstava(transferTransactionAndOneTimePasswDTO));
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
    public void testGetAllTransactionsByDevizniRacunIdTESToriginalToDtoWithId() {

        Long billId = Long.valueOf(1);

        List<Object> transactions = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getAllTransactionsByDevizniRacunId(billId)).thenReturn(transactions);

        try {
            assertEquals(new ResponseEntity<List<Object>>(HttpStatus.OK),transactionControllerTestMyMethods.getAllTransactionsByDevizniRacunId(billId));
        }catch (Exception e) {
            fail(e.getMessage());
        }

    }
    @Test
    public void testGetAllTransactionsByTekuciRacunIdTESToriginalToDtoWithId() {

        Long billId = Long.valueOf(1);

        List<Object> transactions = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getAllTransactionsByTekuciRacunId(billId)).thenReturn(transactions);

        try {
            assertEquals(new ResponseEntity<List<Object>>(HttpStatus.OK),transactionControllerTestMyMethods.getAllTransactionsByTekuciRacunId(billId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetAllTransactionsByPravniRacunIdTESToriginalToDtoWithId() {

        Long billId = Long.valueOf(1);

        List<Object> transactions = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getAllTransactionsByPravniRacunId(billId)).thenReturn(transactions);

        try {
            assertEquals(new ResponseEntity<List<Object>>(HttpStatus.OK),transactionControllerTestMyMethods.getAllTransactionsByPravniRacunId(billId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @Test
    public void testGetUplataTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        Uplata uplata = new Uplata();

        when(transactionControllerTestMyMethods.transakcijaServis.nadjiUplatuPoId(transactionId)).thenReturn(uplata);

        try {
            assertEquals(new ResponseEntity<UplataDTO>(HttpStatus.OK),transactionControllerTestMyMethods.getUplataTransactionById(transactionId));
        }catch (Exception e) {
            fail(e.getMessage());
        }


    }

    //ZA SVE KOJE NAVEDES when..thenReturn SE PRETPOSTAVLJA DA SU TACNE
    //ZA METODU KOJU NE NAVEDES ZNACI DA JE TESTIRAS
    //AKO JE METODA KOJU NE NAVEDES TACNA ONDA JE I WRAPPER METODA TACNA
    //MOZES REDOM DA NAVEDES METODE PA POSLE DE REDOM NE NAVEDES METODE, I AKO JE POSLEDNJA U NIZU NENAVEDENIH TACNA ONDA SU I SVE PRETHODNE NENAVEDENE TACNE!!!!!!!!
    @Test
    public void testGetPrenosSredstavaTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        PrenosSredstava prenosSredstava = new PrenosSredstava();

        when(transactionControllerTestMyMethods.transakcijaServis.nadjiPrenosSretstavaPoId(transactionId)).thenReturn(prenosSredstava);

        try {
            assertEquals(new ResponseEntity<PrenosSredstavaDTO>(HttpStatus.OK),transactionControllerTestMyMethods.getPrenosSredstavaTransactionById(transactionId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testGetSavedPatternsTESToriginalToDtoWithId() {

        List<SablonTransakcije> sabloniTransakcije = new ArrayList<>();

        when(transactionControllerTestMyMethods.transakcijaServis.getSavedTransactionalPatterns()).thenReturn(sabloniTransakcije);

        try {
            assertEquals(new ResponseEntity<List<SablonTransakcijeDTO>>(HttpStatus.OK),transactionControllerTestMyMethods.getSavedPatterns());
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
            assertEquals(new ResponseEntity<String>("Operacija dodavanja sablona transakcije je uspesno izvrsena",HttpStatus.OK),transactionControllerTestMyMethods.addNewTransactionalPattern(noviSablonTransakcijeDTO));
        }catch (Exception e) {
            fail(e.getMessage());
        }

    }
    @Test
    public void testDeleteTransactionalPatternTESTdeleteTransactionalPattern() {

        //PRETPOSTAVLJAMO DA POSTOJI TRANSAKCIONI PATTERN SA ID == 1
        Long transactionalId = Long.valueOf(1);

        try {
            assertEquals(new ResponseEntity<String>("Operacija brisanja transakcije je uspesno izvrsena",HttpStatus.OK),transactionControllerTestMyMethods.deleteTransactionalPattern(transactionalId));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteAllTransactionalPatternsTESTdeleteAllTransactionalPatterns() {

        try {
            assertEquals(new ResponseEntity<String>("Operacija brisanja svih transakcionalnih sablona je uspesno izvrsena",HttpStatus.OK),transactionControllerTestMyMethods.deleteAllTransactionalPatterns());
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }


}
