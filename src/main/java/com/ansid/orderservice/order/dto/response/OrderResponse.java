package com.ansid.orderservice.order.dto.response;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class OrderResponse {
    UUID id;
    UUID customerId;
    BigDecimal totalAmount;
    List<OrderItemResponse> items;
}
