package rs.edu.raf.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
@Getter
@Setter
public class ExchangeWorkingHoursDTO {
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalTime preMarketOpenTime;
    private LocalTime preMarketCloseTime;
    private LocalTime postMarketOpenTime;
    private LocalTime postMarketCloseTime;
    private List<String> holidays;
}
