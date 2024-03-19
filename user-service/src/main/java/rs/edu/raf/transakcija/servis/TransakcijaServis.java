package rs.edu.raf.transakcija.servis;

import rs.edu.raf.korisnik.model.Korisnik;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {


    PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(String id);
    UplataDTO dobaciUplatuSretstavaDTOPoID(String id);


    Korisnik getAllTransactionsByKorisnikId(Long korisnikId);

    List<PrenosSredstavaDTO> dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    List<PrenosSredstavaDTO> dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    //UplataDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
    List<UplataDTO> dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    List<UplataDTO> dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);


    boolean proveraIspravnostiUplataTransakcije(Uplata uplata);
    boolean proveraIspravnostiPrenosSredstavaTransakcije(PrenosSredstava prenosSredstava);

    List<SablonTransakcije> getSavedTransactionalPatterns();

    SablonTransakcije addNewTransactionalPattern(SablonTransakcije sablonTransakcije);

    boolean deleteTransactionalPattern(Long transactionPatternId);
    void deleteAllTransactionalPatterns();


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
