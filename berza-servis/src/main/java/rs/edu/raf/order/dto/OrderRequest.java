package rs.edu.raf.order.dto;

import jakarta.persistence.Column;
import lombok.Data;
import rs.edu.raf.order.model.Enums.Action;
import rs.edu.raf.order.model.Enums.Type;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    private Long userId;

    private String ticker;

    private Integer quantity;

    private BigDecimal limit;

    private BigDecimal stop;

    private boolean allOrNone;

    private boolean margin;

}
