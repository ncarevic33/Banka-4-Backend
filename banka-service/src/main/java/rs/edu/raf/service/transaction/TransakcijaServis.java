package rs.edu.raf.service.transaction;


import rs.edu.raf.model.dto.transaction.NoviPrenosSredstavaDTO;
import rs.edu.raf.model.dto.transaction.NovaUplataDTO;
import rs.edu.raf.model.dto.transaction.PrenosSredstavaDTO;
import rs.edu.raf.model.dto.transaction.UplataDTO;
import rs.edu.raf.model.entities.transaction.PrenosSredstava;
import rs.edu.raf.model.entities.transaction.SablonTransakcije;
import rs.edu.raf.model.entities.transaction.Uplata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {


    //potrebno je obezbediti detaljan pregled svake transakcije po id
    PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(String id);
    UplataDTO dobaciUplatuSretstavaDTOPoID(String id);
    //////////////////////////////////////////////////

    //DODATO
    //potrebno je napraviti opciju za pregled svih transakcija po id klijenta



    //potrebno je napraviti opciju za pregled svih transakcija po racunu

    //PrenosSredstavaDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
                                                        //BROJ RACUNA NA KOJI SE SALJE NOVAC ODNOSNO NA KOJI STIZE
    List<PrenosSredstavaDTO> dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
                                                        //BROJ RACUNA SA KOG STIZE NOVAC ODNOSNO SA KOG SE SALJE
    List<PrenosSredstavaDTO> dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    //UplataDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
    List<UplataDTO> dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    List<UplataDTO> dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
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

    Optional<PrenosSredstava> vratiPrenosSredstavaPoId(String id);

    Optional<Uplata> vratiUplatuPoId(String id);

    PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(String id);

    UplataDTO vratiUplatuDtoPoId(String id);

    List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

    List<UplataDTO> vratiUplataDtoPoRacunuPrimaoca(Long racunPrimaoca);

    List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    List<UplataDTO> vratiUplataDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

    List<PrenosSredstava> vratiPrenosSredstavaUObradi();

    List<Uplata> vratiUplateUObradi();

    BigDecimal izracunajRezervisanaSredstva(Long idRacuna);

    BigDecimal vratiSredstva(Long idRacuna);

    Uplata promeniStatusUplate(String idUplate, String status, Long vremeIzvrsavanja);

    PrenosSredstava promeniStatusPrenosaSredstava(String idPrenosaSredstava, String status, Long vremeIzvrsavanja);


}
