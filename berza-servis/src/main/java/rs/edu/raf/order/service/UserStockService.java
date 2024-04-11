package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.UserStockDto;
import rs.edu.raf.order.dto.UserStockRequest;

import java.util.List;

public interface UserStockService {

    boolean changeUserStockQuantity(UserStockRequest userStockRequest);

    UserStockDto getUserStock(Long userId, String ticker);

    List<UserStockDto> getUserStocks(Long userId);
}
