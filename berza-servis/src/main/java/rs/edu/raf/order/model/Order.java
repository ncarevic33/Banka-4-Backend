package rs.edu.raf.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal limit;

    @Column(nullable = false)
    private BigDecimal stop;

    @Column(nullable = false)
    private boolean allOrNone;

    @Column(nullable = false)
    private boolean margin;

    @Column(nullable = false)
    private String orderAction;

    @Column(nullable = false)
    private String orderType;

    @Column(nullable = false)
    private String orderStatus;

}
