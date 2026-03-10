package com.ansid.orderservice.order.dto.response;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderItemResponse {
    UUID id;
    String productName;
    int quantity;
}
