package rs.edu.raf.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.edu.raf.controller.transaction.TransactionController;
import rs.edu.raf.model.dto.transaction.*;
import rs.edu.raf.service.transaction.TransakcijaServis;


@ExtendWith(MockitoExtension.class)
@Disabled
public class TransactionControllerTest {

/*
    private TransactionController transactionControllerTestMyMethods;

    @Before
    public void setUp() {
        this.transactionControllerTestMyMethods = new TransactionController(
                mock(TransakcijaServis.class));
                //ILI KAO POLJA
                //@Mock TransakcijaServis transakcijaServis
                //@Mock OneTimePasswTokenService oneTimePasswTokenService
                //@Mock DtoOriginalMapper dtoOriginalMapper
                //@InjectMocks TransactionController transactionController

    }


    @Test
    public void testGetUplataTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        UplataDTO uplata = new UplataDTO();

        when(transactionControllerTestMyMethods.transakcijaServis.dobaciUplatuSretstavaDTOPoID(transactionId.toString())).thenReturn(uplata);

        try {
            assertEquals(new ResponseEntity<UplataDTO>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.getUplataTransactionById(transactionId.toString()));
        }catch (Exception e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testGetPrenosSredstavaTransactionByIdTESToriginalToDtoWithId() {

        Long transactionId = Long.valueOf(1);
        PrenosSredstavaDTO prenosSredstava = new PrenosSredstavaDTO();

        when(transactionControllerTestMyMethods.transakcijaServis.dobaviPrenosSretstavaDTOPoID(transactionId.toString())).thenReturn(prenosSredstava);

        try {
            assertEquals(new ResponseEntity<PrenosSredstavaDTO>(HttpStatus.NOT_ACCEPTABLE),transactionControllerTestMyMethods.getPrenosSredstavaTransactionById(transactionId.toString()));
        }catch (Exception e) {
            fail(e.getMessage());
        }
    }




 */

}
