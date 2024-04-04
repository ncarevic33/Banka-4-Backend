package rs.edu.raf.futures.service;

import rs.edu.raf.futures.dto.FuturesContractDto;

import java.util.List;

public interface FuturesService {
    List<FuturesContractDto> findByType(String type);
    List<FuturesContractDto> findByName(String name);
    List<FuturesContractDto> findByKupac(Long kupacId);
    FuturesContractDto buy(Long id, Long kupacId);
}
