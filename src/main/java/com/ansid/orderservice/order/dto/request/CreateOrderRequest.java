package com.ansid.orderservice.order.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Value;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateOrderRequest {

    @NotNull
    UUID customerId;

    @NotNull
    @Positive
    BigDecimal amount;
}
