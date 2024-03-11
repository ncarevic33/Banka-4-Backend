package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.model.Uplata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {


    //potrebno je obezbediti detaljan pregled svake transakcije po id
    PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id);
    UplataDTO dobaciUplatuSretstavaDTOPoID(Long id);
    //////////////////////////////////////////////////

    //DODATO
    //potrebno je napraviti opciju za pregled svih transakcija po id klijenta
    List<Object> getAllTransactionsByKorisnikId(Long korisnikId);


    //potrebno je napraviti opciju za pregled svih transakcija po racunu

    //PrenosSredstavaDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
                                                        //BROJ RACUNA NA KOJI SE SALJE NOVAC ODNOSNO NA KOJI STIZE
    PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
                                                        //BROJ RACUNA SA KOG STIZE NOVAC ODNOSNO SA KOG SE SALJE
    PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    //UplataDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
    UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
    /////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////

    //DODATO
    //potrebno je napraviti zahteve koji primaju neku od transakcija ( placanje ili transfer) kao i jednokratnu lozinku ukoliko je sve ispravno vraca OK
    boolean proveraIspravnostiUplataTransakcije(Uplata uplata);
    boolean proveraIspravnostiPrenosSredstavaTransakcije(PrenosSredstava prenosSredstava);
    ///////////////////////////////////////////////////////////

    //DODATO
    //potrebno je obezbediti listu prethodno sacuvanih sablona sa cesto koriscenim transakcijama
    List<SablonTransakcije> getSavedTransactionalPatterns();

    //DODATO
    //potrebno je obezbediti dodavanje novih omiljenih sablona
    SablonTransakcije addNewTransactionalPattern(SablonTransakcije sablonTransakcije);

    //DODATO
    //potrebno je obezbediti brisanje starih sablona
    boolean deleteTransactionalPattern(Long transactionPatternId);
    void deleteAllTransactionalPatterns();
    ///////////////////////////////////////////////////////////////////

    PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO);

    Uplata sacuvajUplatu(NovaUplataDTO novaUplataDTO);

    Optional<PrenosSredstava> vratiPrenosSredstavaPoId(Long id);

    Optional<Uplata> vratiUplatuPoId(Long id);

    PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id);

    UplataDTO vratiUplatuDtoPoId(Long id);

    List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

    List<UplataDTO> vratiUplataDtoPoRacunuPrimaoca(Long racunPrimaoca);

    List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    List<UplataDTO> vratiUplataDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    List<PrenosSredstava> vratiPrenosSredstavaUObradi();

    List<Uplata> vratiUplateUObradi();

    BigDecimal izracunajRezervisanaSredstva(Long idRacuna);

    BigDecimal vratiSredstva(Long idRacuna);

    Uplata promeniStatusUplate(Long idUplate, String status, Long vremeIzvrsavanja);

    PrenosSredstava promeniStatusPrenosaSredstava(Long idPrenosaSredstava, String status, Long vremeIzvrsavanja);


}
