package com.spring.order.response;

import com.spring.order.domain.Address;
import com.spring.order.domain.Order;
import com.spring.order.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleOrderResponse {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    public SimpleOrderResponse(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        status = order.getStatus();
        address = order.getDelivery().getAddress();

    }
}
