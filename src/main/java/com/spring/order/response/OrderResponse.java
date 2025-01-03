package com.spring.order.response;

import com.spring.order.domain.Address;
import com.spring.order.domain.Order;
import com.spring.order.domain.OrderItem;
import com.spring.order.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OrderResponse {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;
    private List<OrderItemResponse> orderItems;

    public OrderResponse(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        status = order.getStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(o -> new OrderItemResponse(o))
                .collect(toList());
    }

    @Getter
    static class OrderItemResponse {
        private String itemName;
        private int orderPrice;
        private int quantity;

        public OrderItemResponse(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            quantity = orderItem.getCount();
        }
    }
}
