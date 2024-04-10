package rs.edu.raf.order.service;

import rs.edu.raf.order.dto.OrderDto;
import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Enums.Action;
import rs.edu.raf.order.model.Order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;


public interface OrderService {

    OrderDto placeOrder(OrderRequest orderRequest);

    BigDecimal approximateOrderValue(OrderRequest order);

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersForUser(Long userId);

    List<Order> findAllBuyOrdersForTicker(String ticker);

    List<Order> findAllSellOrdersForTicker(String ticker);

}
