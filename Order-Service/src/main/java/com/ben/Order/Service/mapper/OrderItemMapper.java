package com.ben.Order.Service.mapper;

import com.ben.Order.Service.entity.OrderItem;
import com.ben.Order.Service.model.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderItemMapper {

    public static OrderItemDto toDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productId(orderItem.getProductId())
                .build();
    }

    public static OrderItem toEntity(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .id(orderItemDto.getId())
                .quantity(orderItemDto.getQuantity())
                .price(orderItemDto.getPrice())
                .productId(orderItemDto.getProductId())
                .build();
    }
}
