package rs.edu.raf.transakcija.servis.impl;

import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.repository.*;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransakcijaServisImpl implements TransakcijaServis {

    UplataTransactionRepository uplataTransactionRepository;
    PrenosSredstavaTransactionRepository prenosSredstavaTransactionRepository;
    SablonTransakcijeRepository sablonTransakcijeRepository;

    PravniRacunRepository pravniRacunRepository;
    TekuciRacunRepository tekuciRacunRepository;
    DevizniRacunRepository devizniRacunRepository;

    public TransakcijaServisImpl(UplataTransactionRepository uplataTransactionRepository,
                                 PrenosSredstavaTransactionRepository prenosSredstavaTransactionRepository,
                                 PravniRacunRepository billRepository,
                                 SablonTransakcijeRepository sablonTransakcijeRepository,
                                 TekuciRacunRepository tekuciRacunRepository,
                                 DevizniRacunRepository devizniRacunRepository){
        this.prenosSredstavaTransactionRepository = prenosSredstavaTransactionRepository;
        this.uplataTransactionRepository = uplataTransactionRepository;
        this.sablonTransakcijeRepository = sablonTransakcijeRepository;

        this.pravniRacunRepository = billRepository;
        this.devizniRacunRepository = devizniRacunRepository;
        this.tekuciRacunRepository = tekuciRacunRepository;


    }

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id) {
        return null;
    }

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id) {
        return null;
    }

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca) {
        return null;
    }

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPrimaoca(Long brojPrimaoca) {
        return null;
    }

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca) {
        return null;
    }

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoBrojuPosiljaoca(Long brojPosiljaoca) {
        return null;
    }

    @Override
    public List<PrenosSredstava> nadjiPrenosSretstavaKojiSuUObradi() {
        return null;
    }

    @Override
    public List<Uplata> nadjiUplateKojiSuUObradi() {
        return null;
    }

    @Override
    public String izracunajRezervisaneResurse(Long idRacuna) {
        return null;
    }

    @Override
    public boolean proveriDaLiNaRacunuImaDovoljnoSredstavaZaObradu(Long idRacuna, Long idPrenosa) {
        return false;
    }
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public PrenosSredstava nadjiPrenosSretstavaPoId(Long transactionId) {
        return prenosSredstavaTransactionRepository.findById(transactionId).get();
    }

    @Override
    public Uplata nadjiUplatuPoId(Long transactionId) {
        return uplataTransactionRepository.findById(transactionId).get();
    }


    @Override
    public List<Object> getAllTransactionsByKorisnikId(Long clientId) {

        List<Object> allPravniRacunKlijenta;
        List<Object> allTekuciRacunKlijenta;
        List<Object> allDevizniRacunKlijenta;

        List<Object> allTransactions = new ArrayList<>();


        allPravniRacunKlijenta = pravniRacunRepository.findAllBillsByClientId(clientId);
        allTekuciRacunKlijenta = tekuciRacunRepository.findAllBillsByClientId(clientId);
        allDevizniRacunKlijenta = devizniRacunRepository.findAllBillsByClientId(clientId);

        /*for(Object clientBill:allPravniRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((PravniRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((PravniRacun)clientBill).getId()));
        }
        for(Object clientBill:allTekuciRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((TekuciRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((TekuciRacun)clientBill).getId()));
        }
        for(Object clientBill:allDevizniRacunKlijenta) {
            allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(((DevizniRacun)clientBill).getId()));
            allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(((DevizniRacun)clientBill).getId()));
        }*/
        return allTransactions;
    }

    @Override
    public List<Object> getAllTransactionsByTekuciRacunId(Long billId) {

        List<Object> allTransactions = new ArrayList<>();

        /*allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(billId));
        allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(billId));
*/
        return allTransactions;
    }
    @Override
    public List<Object> getAllTransactionsByPravniRacunId(Long billId) {

        List<Object> allTransactions = new ArrayList<>();

        /*allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(billId));
        allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(billId));
*/
        return allTransactions;
    }
    @Override
    public List<Object> getAllTransactionsByDevizniRacunId(Long billId) {

        List<Object> allTransactions = new ArrayList<>();

        /*allTransactions.addAll(uplataTransactionRepository.findAllUplataByBillId(billId));
        allTransactions.addAll(prenosSredstavaTransactionRepository.findAllPrenosSredstavaByBillId(billId));
*/
        return allTransactions;
    }
    @Override
    public boolean proveraIspravnostiUplataTransakcije(Uplata uplata) {

        return false;
    }

    @Override
    public boolean proveraIspravnostiPrenosSredstavaTransakcije(PrenosSredstava prenosSredstava) {
        return false;
    }

    @Override
    public List<SablonTransakcije> getSavedTransactionalPatterns() {
        return this.sablonTransakcijeRepository.findAll();
    }

    @Override
    public void addNewTransactionalPattern(SablonTransakcije sablonTransakcije) {
         this.sablonTransakcijeRepository.save(sablonTransakcije);
    }

    @Override
    public void deleteTransactionalPattern(Long transactionPatternId) {
         this.sablonTransakcijeRepository.deleteById(transactionPatternId);
    }

    @Override
    public void deleteAllTransactionalPatterns() {
        this.sablonTransakcijeRepository.deleteAll();
    }

}
