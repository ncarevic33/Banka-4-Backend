package rs.edu.raf.futures.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.futures.dto.FuturesContractDto;
import rs.edu.raf.futures.model.FuturesContract;

@Component
public class FuturesContractMapper {

    public FuturesContractDto futuresContractToFutureContractDto(FuturesContract futuresContract) {
        FuturesContractDto futuresContractDto = new FuturesContractDto();
        futuresContractDto.setContractSize(futuresContract.getContractSize());
        futuresContractDto.setContractUnit(futuresContract.getContractUnit());
        futuresContractDto.setPrice(futuresContract.getPrice());
        futuresContractDto.setType(futuresContract.getType());
        futuresContractDto.setName(futuresContract.getName());
        futuresContractDto.setMaintenanceMargin(futuresContract.getMaintenanceMargin());
        futuresContractDto.setSettlementDate(System.currentTimeMillis());
        return futuresContractDto;
    }
}
