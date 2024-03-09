package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;

public interface TransakcijaServis {



   // potrebno je napraviti opciju za pregled svih transakcija po racunu


    //potrebno je obezbediti detaljan pregled svake transakcije po id
    public PrenosSredstava nadjiPrenosSretstavaPoId(Long id);
    public Uplata nadjiUplatuPoId(Long id);
    //////////////////////////////////////////////////

    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id);
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    public List<PrenosSredstava> nadjiPrenosSretstavaKojiSuUObradi();
    public List<Uplata> nadjiUplateKojiSuUObradi();
    public String izracunajRezervisaneResurse(Long idRacuna);
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);


    ///////////////////////////////////////////////////////////////////
    //potrebno je napraviti opciju za pregled svih transakcija po id klijenta
    List<Object> getAllTransactionsByKorisnikId(Long clientId);

    List<Object> getAllTransactionsByPravniRacunId(Long billId);
    List<Object> getAllTransactionsByTekuciRacunId(Long billId);
    List<Object> getAllTransactionsByDevizniRacunId(Long billId);


    boolean proveraIspravnostiUplataTransakcije(Uplata uplata);
    boolean proveraIspravnostiPrenosSredstavaTransakcije(PrenosSredstava prenosSredstava);

    //potrebno je obezbediti listu prethodno sacuvanih sablona sa cesto koriscenim transakcijama
    List<SablonTransakcije> getSavedTransactionalPatterns();

    //potrebno je obezbediti dodavanje novih omiljenih sablona
    void addNewTransactionalPattern(SablonTransakcije sablonTransakcije);

    //potrebno je obezbediti brisanje starih sablona
    void deleteTransactionalPattern(Long transactionPatternId);
    void deleteAllTransactionalPatterns();
    ///////////////////////////////////////////////////////////////////
}
