package com.spring.order.controller;

import com.spring.order.response.OrderResponse;
import com.spring.order.response.Result;
import com.spring.order.response.SimpleOrderResponse;
import com.spring.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("orders-simple")
    public Result<List<SimpleOrderResponse>> getSimpleOrders() {
        List<SimpleOrderResponse> result = orderService.findAllWithoutItem();
        return new Result<>(result.size(), result);
    }

    @GetMapping("orders")
    public Result<List<OrderResponse>> getOrders(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam("size") int size
    ) {
        List<OrderResponse> orders = orderService.findAllWithItem(page, size);
        return new Result<>(orders.size(), orders);
    }

    @GetMapping("orders-simple/non-fetch_join")
    public Result<List<SimpleOrderResponse>> getSimpleOrders_non_fetch_join() {
        List<SimpleOrderResponse> result = orderService.findAllWithoutItem_non_fetch_join();
        return new Result<>(result.size(), result);
    }

    @GetMapping("orders/non-fetch_join")
    public Result<List<OrderResponse>> getOrders_non_fetch_join() {
        List<OrderResponse> result = orderService.findAllWithItem_non_fetch_join();
        return new Result<>(result.size(), result);
    }

}
