package rs.edu.raf.exchange.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.exchange.dto.ExchangeWorkingHoursDTO;
import rs.edu.raf.exchange.model.ExchangeWorkingHours;

import java.time.LocalTime;
@Component
public class ExchangeWorkingHoursMapper {

    public ExchangeWorkingHoursDTO exchangeWorkingHoursToDTO(ExchangeWorkingHours schedule) {
        ExchangeWorkingHoursDTO dto = new ExchangeWorkingHoursDTO();
        dto.setOpenTime(LocalTime.parse(schedule.getOpenTime()));
        dto.setCloseTime(LocalTime.parse(schedule.getCloseTime()));
        dto.setPreMarketOpenTime(LocalTime.parse(schedule.getPreMarketOpenTime()));
        dto.setPreMarketCloseTime(LocalTime.parse(schedule.getPreMarketCloseTime()));
        dto.setPostMarketOpenTime(LocalTime.parse(schedule.getPostMarketOpenTime()));
        dto.setPostMarketCloseTime(LocalTime.parse(schedule.getPostMarketCloseTime()));
        dto.setHolidays(schedule.getHolidays());

        return dto;
    }
}
