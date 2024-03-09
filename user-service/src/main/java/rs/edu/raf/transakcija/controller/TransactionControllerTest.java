/*package rs.edu.raf.transakcija.controller;

import org.springframework.http.ResponseEntity;

public class TransactionControllerTest {

    private TransactionController transactionController;

    @Before("testDeleteAllTransactionalPatterns")
    public void setUp() {
        this.transactionController = new TransactionController();

    }

    @Test
    public void testDeleteAllTransactionalPatterns() {

        //Pozivamo metodu koju testiramo
        ResponseEntity<String> response = transactionController.deleteAllTransactionalPatterns();

        //Proveravamo da li je odgovor uspešan (HTTP status kod 200)
        assertEquals(200, response.getStatusCodeValue());

        //Proveravamo da li je telo odgovora očekivano
        assertEquals("Operacija brisanja svih transakcija je uspesno izvrsena", response.getBody());
    }


}
*/