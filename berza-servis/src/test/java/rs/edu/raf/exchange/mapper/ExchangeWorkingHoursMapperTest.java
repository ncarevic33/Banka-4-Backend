package rs.edu.raf.exchange.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.edu.raf.exchange.dto.ExchangeWorkingHoursDTO;
import rs.edu.raf.exchange.model.ExchangeWorkingHours;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExchangeWorkingHoursMapperTest {

    private ExchangeWorkingHoursMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ExchangeWorkingHoursMapper();
    }

    @Test
    public void testExchangeWorkingHoursToDTO() {
        // Priprema podataka za test
        ExchangeWorkingHours schedule = new ExchangeWorkingHours();
        schedule.setOpenTime("08:00:00");
        schedule.setCloseTime("16:00:00");
        schedule.setPreMarketOpenTime("07:30:00");
        schedule.setPreMarketCloseTime("08:00:00");
        schedule.setPostMarketOpenTime("16:00:00");
        schedule.setPostMarketCloseTime("16:30:00");

        List<String> holidays = new ArrayList<>();
        holidays.add("2024-05-01");
        holidays.add("2024-05-09");
        schedule.setHolidays(holidays);


        ExchangeWorkingHoursDTO dto = mapper.exchangeWorkingHoursToDTO(schedule);


        Assertions.assertNotNull(dto);
        Assertions.assertEquals(LocalTime.parse("08:00:00"), dto.getOpenTime());
        Assertions.assertEquals(LocalTime.parse("16:00:00"), dto.getCloseTime());
        Assertions.assertEquals(LocalTime.parse("07:30:00"), dto.getPreMarketOpenTime());
        Assertions.assertEquals(LocalTime.parse("08:00:00"), dto.getPreMarketCloseTime());
        Assertions.assertEquals(LocalTime.parse("16:00:00"), dto.getPostMarketOpenTime());
        Assertions.assertEquals(LocalTime.parse("16:30:00"), dto.getPostMarketCloseTime());
        Assertions.assertEquals(holidays, dto.getHolidays());
    }
}
