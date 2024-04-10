package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.UserStockDto;

import java.util.List;

public interface UserStockService {

    boolean changeUserStockQuantity(Long userId, String ticker, Integer quantity);

    UserStockDto getUserStock(Long userId, String ticker);

    List<UserStockDto> getUserStocks(Long userId);
}
