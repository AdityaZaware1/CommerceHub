package com.ben.Order.Service.service;

import com.ben.Order.Service.model.dto.OrderDto;
import com.ben.Order.Service.model.response.PaymentResponse;

import java.util.List;

public interface OrderService {

    PaymentResponse createOrder(OrderDto orderDto);

    OrderDto getOrderById(Long orderId);

    OrderDto confirmOrder(Long orderId, OrderDto orderDto);

    List<OrderDto> getOrdersByUserId(Long userId);

    List<OrderDto> getOrdersByBranchId(Long branchId);
}
