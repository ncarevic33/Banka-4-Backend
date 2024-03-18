package rs.edu.raf.order.dto;

import lombok.Data;
import rs.edu.raf.order.model.OrderAction;
import rs.edu.raf.order.model.OrderType;

@Data
public class OrderRequest {

    private String ticker;
    private Integer quantity;
    private OrderAction orderAction;
    private OrderType orderType;
    private Double limit;
    private Double stop;

}
