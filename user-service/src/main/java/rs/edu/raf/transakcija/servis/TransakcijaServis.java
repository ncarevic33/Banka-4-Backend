package rs.edu.raf.transakcija.servis;

import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Uplata;

import java.util.List;
import java.util.Optional;

public interface TransakcijaServis {


    public PrenosSredstava nadjiPrenosSretstavaPoId(Long id);
    public Uplata nadjiUplatuPoId(Long id);


    //potrebno je obezbediti detaljan pregled svake transakcije po id
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id);
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id);
    //////////////////////////////////////////////////

    //DODATO
    //potrebno je napraviti opciju za pregled svih transakcija po id klijenta
    List<Object> getAllTransactionsByKorisnikId(Long korisnikId);


    //potrebno je napraviti opciju za pregled svih transakcija po racunu

    //PrenosSredstavaDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
                                                        //BROJ RACUNA NA KOJI SE SALJE NOVAC ODNOSNO NA KOJI STIZE
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
                                                        //BROJ RACUNA SA KOG STIZE NOVAC ODNOSNO SA KOG SE SALJE
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);

    //UplataDTO KOJI JE KORISNIK SLAO ILI KOJI MU JE STIGAO
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca);
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca);
    /////////////////////////////////////////////////////////
    public List<PrenosSredstava> nadjiPrenosSretstavaKojiSuUObradi();
    public List<Uplata> nadjiUplateKojiSuUObradi();

    public String izracunajRezervisaneResurse(Long idRacuna);
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);


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

    Uplata sacuvajPlacanje(NovaUplataDTO novaUplataDTO);

    Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id);

    Optional<Uplata> nadjiPlacanjePoId(Long id);

     PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id);

     UplataDTO vratiPlacanjeDtoPoId(Long id);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<UplataDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca);

     List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<UplataDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca);

     List<PrenosSredstava> vratiPrenosSredstavaUObradi();

     List<Uplata> vratiPlacanjaUObradi();

     String izracunajRezervisaneResurse(Long idRacuna);

     boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa);

}
