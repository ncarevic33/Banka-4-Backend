package rs.edu.raf.exchange.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "exchange_schedules")
public class ExchangeWorkingHours {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "exchange_id")
        private Exchange exchange;

        @Column(name = "open_time")
        private String openTime;

        @Column(name = "close_time")
        private String closeTime;

        @Column(name = "pre_market_open_time")
        private String preMarketOpenTime;

        @Column(name = "pre_market_close_time")
        private String preMarketCloseTime;

        @Column(name = "post_market_open_time")
        private String postMarketOpenTime;

        @Column(name = "post_market_close_time")
        private String postMarketCloseTime;

        @ElementCollection
        @CollectionTable(name = "exchange_holidays", joinColumns = @JoinColumn(name = "exchange_schedule_id"))
        @Column(name = "holiday_date")
        private List<String> holidays;

}
