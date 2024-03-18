package rs.edu.raf.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private Integer quantity;

    private Double limit;
    private Double stop;

    @Column(nullable = false)
    private OrderAction orderAction;

    @Column(nullable = false)
    private OrderType orderType;

}
