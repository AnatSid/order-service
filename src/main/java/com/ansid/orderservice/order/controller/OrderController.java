package com.ansid.orderservice.order.controller;

import com.ansid.orderservice.order.dto.request.CreateOrderRequest;
import com.ansid.orderservice.order.dto.response.OrderResponse;
import com.ansid.orderservice.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Orders", description = "Operations with orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Create new order")
    @PostMapping
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request.getCustomerId(), request.getAmount());
    }

    @Operation(summary = "Get order by id")
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @GetMapping
    public Page<OrderResponse> getOrders(@ParameterObject Pageable pageable) {
        return orderService.getOrders(pageable);
    }

}
