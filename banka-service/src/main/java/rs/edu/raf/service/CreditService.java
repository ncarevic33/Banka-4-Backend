package rs.edu.raf.service;

import rs.edu.raf.model.dto.CreditRequestDto;
import rs.edu.raf.model.dto.DetailedCreditDto;
import rs.edu.raf.model.entities.CreditRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CreditService {

    public String applyForCredit(CreditRequestDto creditRequestDto);
    public String approveCreditRequest(Long id);
    public String dennyCreditRequest(Long id);
    public List<CreditRequestDto> getAllCreditRequests(String status);
    public List<CreditRequestDto> getAllCreditRequestForUser(Long userId, String status);
    public void createCredit(CreditRequest creditRequest, String tipRacuna, String currency, BigDecimal salary);
    public DetailedCreditDto getDetailedCredit(Long creditRequestId);

}
