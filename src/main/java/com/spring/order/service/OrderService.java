package com.spring.order.service;

import com.spring.order.domain.Order;
import com.spring.order.repository.OrderRepository;
import com.spring.order.response.OrderResponse;
import com.spring.order.response.SimpleOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<SimpleOrderResponse> findAllWithoutItem() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(o->new SimpleOrderResponse(o))
                .collect(toList());
    }

    public List<OrderResponse> findAllWithItem(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAllWithMemberDeliveryItem(pageRequest);
        return orderPage.getContent().stream()
                .map(o->new OrderResponse(o))
                .collect(toList());
    }

    public List<SimpleOrderResponse> findAllWithoutItem_non_fetch_join() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(o->new SimpleOrderResponse(o))
                .collect(toList());
    }

    public List<OrderResponse> findAllWithItem_non_fetch_join() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(o->new OrderResponse(o))
                .collect(toList());
    }
}
