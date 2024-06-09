package rs.edu.raf.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PonudaDTO {
    private Long id;
    private String ticker;

    private Integer quantity;

    private BigDecimal amountOffered;
}
