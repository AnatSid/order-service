package com.ansid.orderservice.order.mapper;

import com.ansid.orderservice.order.dto.response.OrderItemResponse;
import com.ansid.orderservice.order.dto.response.OrderResponse;
import com.ansid.orderservice.order.entity.OrderEntity;
import com.ansid.orderservice.order.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(OrderEntity entity);

    OrderItemResponse toResponse(OrderItemEntity entity);

}
