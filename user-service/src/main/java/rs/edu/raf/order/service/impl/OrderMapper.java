package rs.edu.raf.order.service.impl;

import rs.edu.raf.order.dto.OrderRequest;
import rs.edu.raf.order.model.Order;

public class OrderMapper {

    public static Order mapOrderRequestToOrder(OrderRequest orderRequest) {
        return Order.builder()
                .ticker(orderRequest.getTicker())
                .quantity(orderRequest.getQuantity())
                .orderAction(orderRequest.getOrderAction())
                .orderType(orderRequest.getOrderType())
                .limit(orderRequest.getLimit())
                .stop(orderRequest.getStop())
                .build();
    }
}
