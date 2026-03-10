package com.ansid.orderservice.order.service;

import com.ansid.orderservice.order.dto.response.OrderResponse;
import com.ansid.orderservice.order.entity.OrderEntity;

import com.ansid.orderservice.order.exception.OrderNotFoundException;
import com.ansid.orderservice.order.mapper.OrderMapper;
import com.ansid.orderservice.order.repository.OrderRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Transactional
    public OrderResponse createOrder(UUID customerId, BigDecimal amount) {
        var order = new OrderEntity(customerId, amount);
        order.addItem("Laptop", 1);
        order.addItem("Mouse", 2);

        OrderEntity saved = orderRepository.save(order);

        return orderMapper.toResponse(saved);
    }


    @Transactional(readOnly = true)
    public OrderResponse getById(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return orderMapper.toResponse(orderEntity);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponse> getOrders(Pageable pageable) {
        Page<OrderEntity> page = orderRepository.findAll(pageable);

        return page.map(orderMapper::toResponse);
    }

}
