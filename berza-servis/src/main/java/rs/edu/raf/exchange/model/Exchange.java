package rs.edu.raf.exchange.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;

@Setter
@Getter
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exchange_name")
    private String exchangeName;

    @Column(name = "exchange_acronym")
    private String exchangeAcronym;

    @Column(name = "exchange_mic_code")
    private String exchangeMICCode;

    @Column(name = "polity")
    private String polity;

    @Column(name = "currency")
    private String currency;

    @Column(name = "time_zone")
    private ZoneId timeZone;

    @OneToOne(mappedBy = "exchange", cascade = CascadeType.ALL)
    private ExchangeWorkingHours exchangeSchedule;


    /*public ZonedDateTime getCurrentTime() {
        try {
            return ZonedDateTime.now(this.timeZone);
        } catch (ZoneRulesException e) {
            System.err.println("TimeZone rules not found: " + e.getMessage());
            return null;
        }
    }*/

}
