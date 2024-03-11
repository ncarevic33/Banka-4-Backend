package rs.edu.raf.transakcija.servis.impl;

import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.mapper.DtoOriginalMapper;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.SablonTransakcije;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.repository.*;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.NovaUplataDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.repo.PlacanjaRepozitorijum;
import rs.edu.raf.transakcija.repo.PrenosSredstavaRepozitorijum;
import rs.edu.raf.transakcija.servis.TransakcijaMapper;
import rs.edu.raf.transakcija.servis.TransakcijaServis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransakcijaServisImpl implements TransakcijaServis {

    UplataTransactionRepository uplataTransactionRepository;
    PrenosSredstavaTransactionRepository prenosSredstavaTransactionRepository;
    SablonTransakcijeRepository sablonTransakcijeRepository;

    PravniRacunRepository pravniRacunRepository;
    TekuciRacunRepository tekuciRacunRepository;
    DevizniRacunRepository devizniRacunRepository;

    DtoOriginalMapper dtoOriginalMapper;

    public TransakcijaServisImpl(UplataTransactionRepository uplataTransactionRepository,
                                 PrenosSredstavaTransactionRepository prenosSredstavaTransactionRepository,
                                 PravniRacunRepository billRepository,
                                 SablonTransakcijeRepository sablonTransakcijeRepository,
                                 TekuciRacunRepository tekuciRacunRepository,
                                 DevizniRacunRepository devizniRacunRepository,
                                 DtoOriginalMapper dtoOriginalMapper){
        this.prenosSredstavaTransactionRepository = prenosSredstavaTransactionRepository;
        this.uplataTransactionRepository = uplataTransactionRepository;
        this.sablonTransakcijeRepository = sablonTransakcijeRepository;

        this.pravniRacunRepository = billRepository;
        this.devizniRacunRepository = devizniRacunRepository;
        this.tekuciRacunRepository = tekuciRacunRepository;
        this.dtoOriginalMapper = dtoOriginalMapper;


    }

    @Override
    public PrenosSredstavaDTO dobaviPrenosSretstavaDTOPoID(Long id) {

        if(id != null)
            return (PrenosSredstavaDTO) dtoOriginalMapper.originalToDtoWithId(prenosSredstavaTransactionRepository.findById(id).get());
        return null;
    }

    @Override
    public UplataDTO dobaciUplatuSretstavaDTOPoID(Long id) {
        if(id != null)
            return (UplataDTO) dtoOriginalMapper.originalToDtoWithId(uplataTransactionRepository.findById(id).get());
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
    private final PlacanjaRepozitorijum placanjaRepozitorijum;
    private final PrenosSredstavaRepozitorijum prenosSredstavaRepozitorijum;

    @Autowired
    public TransakcijaServisImpl(PlacanjaRepozitorijum placanjaRepozitorijum, PrenosSredstavaRepozitorijum prenosSredstavaRepozitorijum) {
        this.placanjaRepozitorijum = placanjaRepozitorijum;
        this.prenosSredstavaRepozitorijum = prenosSredstavaRepozitorijum;
    }

    @Override
    public PrenosSredstava sacuvajPrenosSredstava(NoviPrenosSredstavaDTO noviPrenosSredstavaDTO) {
        return prenosSredstavaRepozitorijum.save(TransakcijaMapper.NoviPrenosSredstavaDtoToEntity(noviPrenosSredstavaDTO));
    }

    @Override
    public Uplata sacuvajPlacanje(NovaUplataDTO novaUplataDTO) {
        return placanjaRepozitorijum.save(TransakcijaMapper.NovoPlacanjeDtoToEntity(novaUplataDTO));
    }

    @Override
    public Optional<PrenosSredstava> nadjiPrenosSredstavaPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id);
    }

    @Override
    public Optional<Uplata> nadjiPlacanjePoId(Long id) {
        return placanjaRepozitorijum.findById(id);
    }

    @Override
    public PrenosSredstavaDTO vratiPrenosSredstavaDtoPoId(Long id) {
        return prenosSredstavaRepozitorijum.findById(id)
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .orElseThrow(() -> new EntityNotFoundException("Prenos sredstava sa ID-om " + id + " nije pronađen."));
    }

    @Override
    public UplataDTO vratiPlacanjeDtoPoId(Long id) {
        return placanjaRepozitorijum.findById(id)
                .map(TransakcijaMapper::PlacanjeToDto)
                .orElseThrow(() -> new EntityNotFoundException("Placanje sa ID-om " + id + " nije pronađeno."));
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return prenosSredstavaRepozitorijum.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiPlacanjeDtoPoRacunuPrimaoca(Long racunPrimaoca) {
        return placanjaRepozitorijum.findAllByRacunPrimaoca(racunPrimaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstavaDTO> vratiPrenosSredstavaDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return prenosSredstavaRepozitorijum.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PrenosSredstavaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UplataDTO> vratiPlacanjeDtoPoRacunuPosiljaoca(Long racunPosiljaoca) {
        return placanjaRepozitorijum.findAllByRacunPosiljaoca(racunPosiljaoca).stream()
                .map(TransakcijaMapper::PlacanjeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrenosSredstava> vratiPrenosSredstavaUObradi() {
        return prenosSredstavaRepozitorijum.findAllByStatus(Status.U_OBRADI);
    }

    @Override
    public List<Uplata> vratiPlacanjaUObradi() {
        return placanjaRepozitorijum.findAllByStatus(Status.U_OBRADI);
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
        return null;
    }

    @Override
    public Uplata nadjiUplatuPoId(Long transactionId) {
       return null;
    }


    public List<Object> getAllTransactionsByKorisnikId(Long clientId) {


        List<Object> allTransactions = new ArrayList<>();

        if(clientId != null) {
            List<Object> allPravniRacunKlijenta;
            List<Object> allTekuciRacunKlijenta;
            List<Object> allDevizniRacunKlijenta;

            /*
        allPravniRacunKlijenta = pravniRacunRepository.findAllBillsByClientId(clientId);
        allTekuciRacunKlijenta = tekuciRacunRepository.findAllBillsByClientId(clientId);
        allDevizniRacunKlijenta = devizniRacunRepository.findAllBillsByClientId(clientId);

        for(Object clientBill:allPravniRacunKlijenta) {
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
        return null;
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
    public SablonTransakcije addNewTransactionalPattern(SablonTransakcije sablonTransakcije) {
        if(sablonTransakcije != null) {           //AKO VEC POSTOJI AZURIRACE,AKO NE POSTOJI DODACE
            return this.sablonTransakcijeRepository.save(sablonTransakcije);
        }
        return null;
    }

    @Override
    public boolean deleteTransactionalPattern(Long transactionPatternId) {

        if(transactionPatternId != null){
                                        //NE VRACA GRESKU AKO NE POSTOJI ID
         this.sablonTransakcijeRepository.deleteById(transactionPatternId);
         return true;
        }
        return false;
    }

    @Override
    public void deleteAllTransactionalPatterns() {
                                        //NE VRACA GRESKU AKO NEMA STA DA SE BRISE
        this.sablonTransakcijeRepository.deleteAll();
    }

}
