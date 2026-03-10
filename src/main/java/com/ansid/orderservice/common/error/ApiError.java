package com.ansid.orderservice.common.error;

import lombok.Value;

import java.util.List;

@Value
public class ApiError {
    String error;
    String message;
    List<String> details;
}
