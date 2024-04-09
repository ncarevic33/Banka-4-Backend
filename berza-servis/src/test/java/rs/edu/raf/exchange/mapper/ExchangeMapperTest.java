package rs.edu.raf.exchange.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rs.edu.raf.exchange.dto.ExchangeDTO;
import rs.edu.raf.exchange.model.Exchange;

import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ExchangeMapperTest {

    private ExchangeMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ExchangeMapper();
    }

    @Test
    public void testExchangeToExchangeDTO() {

        Exchange exchange = new Exchange();
        exchange.setExchangeName("Stock Exchange");
        exchange.setExchangeAcronym("SE");
        exchange.setExchangeMICCode("XYZ");
        exchange.setPolity("Country");
        exchange.setCurrency("USD");
        exchange.setTimeZone(ZoneId.of("Europe/Belgrade"));


        ExchangeDTO dto = mapper.exchangeToExchangeDTO(exchange);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(exchange.getExchangeName(), dto.getExchangeName());
        Assertions.assertEquals(exchange.getExchangeAcronym(), dto.getExchangeAcronym());
        Assertions.assertEquals(exchange.getExchangeMICCode(), dto.getExchangeMICCode());
        Assertions.assertEquals(exchange.getPolity(), dto.getPolity());
        Assertions.assertEquals(exchange.getCurrency(), dto.getCurrency());
        Assertions.assertEquals(exchange.getTimeZone().getId(), dto.getTimeZone());
    }

}
