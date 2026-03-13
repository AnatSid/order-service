package com.ansid.orderservice.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Value;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateOrderRequest {

    @NotNull
    @Schema(description = "Customer identifier")
    UUID customerId;

    @NotNull
    @Positive
    @Schema(description = "Total order amount")
    BigDecimal amount;
}
