package com.ben.Order.Service.mapper;

import com.ben.Order.Service.entity.Order;
import com.ben.Order.Service.entity.OrderItem;
import com.ben.Order.Service.model.dto.OrderDto;
import com.ben.Order.Service.model.dto.OrderItemDto;

import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order, List<OrderItemDto> items) {
        return OrderDto.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .paymentType(order.getPaymentType())
                .status(order.getStatus())
                .branchId(order.getBranchId())
                .userId(order.getUserId())
                .items(items)
                .build();
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .paymentType(order.getPaymentType())
                .status(order.getStatus())
                .branchId(order.getBranchId())
                .userId(order.getUserId())
                .items(order.getItems().stream().map(OrderItemMapper::toDto).toList())
                .build();
    }

    public static Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .amount(orderDto.getAmount())
                .paymentType(orderDto.getPaymentType())
                .status(orderDto.getStatus())
                .branchId(orderDto.getBranchId())
                    .userId(orderDto.getUserId())
                    .items(orderDto.getItems().stream().map(OrderItemMapper::toEntity).toList())
                .build();
    }

    public static Order toEntity(OrderDto orderDto, List<OrderItem> items) {
        return Order.builder()
                .id(orderDto.getId())
                .amount(orderDto.getAmount())
                .paymentType(orderDto.getPaymentType())
                .status(orderDto.getStatus())
                .items(items)
                .branchId(orderDto.getBranchId())
                .userId(orderDto.getUserId())
                .build();
    }
}
