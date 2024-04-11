package rs.edu.raf.order.dto;

import lombok.Data;

@Data
public class UserStockRequest {

        private Long userId;
        private String ticker;
        private Integer quantity;

}
