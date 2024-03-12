package rs.edu.raf.transakcija.servis.impl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.edu.raf.transakcija.dto.NoviPrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.PrenosSredstavaDTO;
import rs.edu.raf.transakcija.dto.UplataDTO;
import rs.edu.raf.transakcija.model.PrenosSredstava;
import rs.edu.raf.transakcija.model.Status;
import rs.edu.raf.transakcija.model.Uplata;
import rs.edu.raf.transakcija.repository.PravniRacunRepository;
import rs.edu.raf.transakcija.repository.PrenosSredstavaRepository;
import rs.edu.raf.transakcija.repository.UplataRepository;
import rs.edu.raf.transakcija.servis.TransakcijaMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransakcijaServisImplTest {

    @Mock
    private UplataRepository uplataRepository;

    @Mock
    private PrenosSredstavaRepository prenosSredstavaRepository;

    @InjectMocks
    private TransakcijaServisImpl transakcijaServis;

    @Test
    public void givenValidId_whenVratiPrenosSredstavaDtoPoRacunuPrimaoca_thenReturnPrenosSredstavaDTOList() {
        Long racunPrimaoca = 1L;
        List<PrenosSredstavaDTO> expected = new ArrayList<>();
        when(prenosSredstavaRepository.findAllByRacunPrimaoca(racunPrimaoca)).thenReturn(new ArrayList<>());

        List<PrenosSredstavaDTO> result = transakcijaServis.vratiPrenosSredstavaDtoPoRacunuPrimaoca(racunPrimaoca);

        assertEquals(expected, result);
    }

    @Test
    public void givenValidId_whenVratiUplataDtoPoRacunuPrimaoca_thenReturnUplataDTOList() {
        Long racunPrimaoca = 1L;
        List<UplataDTO> expected = new ArrayList<>();
        when(uplataRepository.findAllByRacunPrimaoca(racunPrimaoca)).thenReturn(new ArrayList<>());

        List<UplataDTO> result = transakcijaServis.vratiUplataDtoPoRacunuPrimaoca(racunPrimaoca);

        assertEquals(expected, result);
    }

    @Test
    public void givenValidId_whenVratiPrenosSredstavaDtoPoRacunuPosiljaoca_thenReturnPrenosSredstavaDTOList() {
        Long racunPosiljaoca = 1L;
        List<PrenosSredstavaDTO> expected = new ArrayList<>();
        when(prenosSredstavaRepository.findAllByRacunPosiljaoca(racunPosiljaoca)).thenReturn(new ArrayList<>());

        List<PrenosSredstavaDTO> result = transakcijaServis.vratiPrenosSredstavaDtoPoRacunuPosiljaoca(racunPosiljaoca);

        assertEquals(expected, result);
    }

    @Test
    public void givenValidId_whenVratiUplataDtoPoRacunuPosiljaoca_thenReturnUplataDTOList() {
        Long racunPosiljaoca = 1L;
        List<UplataDTO> expected = new ArrayList<>();
        when(uplataRepository.findAllByRacunPosiljaoca(racunPosiljaoca)).thenReturn(new ArrayList<>());

        List<UplataDTO> result = transakcijaServis.vratiUplataDtoPoRacunuPosiljaoca(racunPosiljaoca);

        assertEquals(expected, result);
    }

    @Test
    public void whenVratiPrenosSredstavaUObradi_thenReturnPrenosSredstavaList() {
        List<PrenosSredstava> expected = new ArrayList<>();
        when(prenosSredstavaRepository.findAllByStatus(Status.U_OBRADI)).thenReturn(new ArrayList<>());

        List<PrenosSredstava> result = transakcijaServis.vratiPrenosSredstavaUObradi();

        assertEquals(expected, result);
    }

    @Test
    public void whenVratiUplateUObradi_thenReturnUplataList() {
        List<Uplata> expected = new ArrayList<>();
        when(uplataRepository.findAllByStatus(Status.U_OBRADI)).thenReturn(new ArrayList<>());

        List<Uplata> result = transakcijaServis.vratiUplateUObradi();

        assertEquals(expected, result);
    }

    @Test
    public void givenValidIdAndStatus_whenPromeniStatusUplate_thenReturnUpdatedUplata() {
        Long idUplate = 1L;
        String newStatus = "REALIZOVANO";
        Uplata expected = new Uplata();
        expected.setId(idUplate);
        expected.setStatus(newStatus);
        when(uplataRepository.findById(idUplate)).thenReturn(Optional.of(new Uplata()));
        when(uplataRepository.save(any(Uplata.class))).thenReturn(expected);

        Uplata result = transakcijaServis.promeniStatusUplate(idUplate, newStatus, System.currentTimeMillis());

        assertEquals(expected, result);
    }

    @Test
    public void givenValidIdAndStatus_whenPromeniStatusPrenosaSredstava_thenReturnUpdatedPrenosSredstava() {
        Long idPrenosaSredstava = 1L;
        String newStatus = "REALIZOVANO";
        PrenosSredstava expected = new PrenosSredstava();
        expected.setId(idPrenosaSredstava);
        expected.setStatus(newStatus);
        when(prenosSredstavaRepository.findById(idPrenosaSredstava)).thenReturn(Optional.of(new PrenosSredstava()));
        when(prenosSredstavaRepository.save(any(PrenosSredstava.class))).thenReturn(expected);

        PrenosSredstava result = transakcijaServis.promeniStatusPrenosaSredstava(idPrenosaSredstava, newStatus, System.currentTimeMillis());

        assertEquals(expected, result);
    }

    @Test
    public void givenInvalidId_whenVratiPrenosSredstavaDtoPoRacunuPrimaoca_thenReturnEmptyList() {
        Long racunPrimaoca = -1L;
        when(prenosSredstavaRepository.findAllByRacunPrimaoca(racunPrimaoca)).thenReturn(new ArrayList<>());

        List<PrenosSredstavaDTO> result = transakcijaServis.vratiPrenosSredstavaDtoPoRacunuPrimaoca(racunPrimaoca);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenInvalidId_whenVratiUplataDtoPoRacunuPrimaoca_thenReturnEmptyList() {
        Long racunPrimaoca = -1L;
        when(uplataRepository.findAllByRacunPrimaoca(racunPrimaoca)).thenReturn(new ArrayList<>());

        List<UplataDTO> result = transakcijaServis.vratiUplataDtoPoRacunuPrimaoca(racunPrimaoca);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenInvalidId_whenVratiPrenosSredstavaDtoPoRacunuPosiljaoca_thenReturnEmptyList() {
        Long racunPosiljaoca = -1L;
        when(prenosSredstavaRepository.findAllByRacunPosiljaoca(racunPosiljaoca)).thenReturn(new ArrayList<>());

        List<PrenosSredstavaDTO> result = transakcijaServis.vratiPrenosSredstavaDtoPoRacunuPosiljaoca(racunPosiljaoca);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenInvalidId_whenVratiUplataDtoPoRacunuPosiljaoca_thenReturnEmptyList() {
        Long racunPosiljaoca = -1L;
        when(uplataRepository.findAllByRacunPosiljaoca(racunPosiljaoca)).thenReturn(new ArrayList<>());

        List<UplataDTO> result = transakcijaServis.vratiUplataDtoPoRacunuPosiljaoca(racunPosiljaoca);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenInvalidId_whenPromeniStatusUplate_thenReturnEntityNotFoundException() {
        Long idUplate = -1L;
        String newStatus = "REALIZOVANO";
        when(uplataRepository.findById(idUplate)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> transakcijaServis.promeniStatusUplate(idUplate, newStatus, System.currentTimeMillis()));
    }

    @Test
    public void givenInvalidId_whenPromeniStatusPrenosaSredstava_thenReturnEntityNotFoundException() {
        Long idPrenosaSredstava = -1L;
        String newStatus = "REALIZOVANO";
        when(prenosSredstavaRepository.findById(idPrenosaSredstava)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> transakcijaServis.promeniStatusPrenosaSredstava(idPrenosaSredstava, newStatus, System.currentTimeMillis()));
    }
}

