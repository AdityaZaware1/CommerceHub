package com.ben.Order.Service.controller;

import com.ben.Order.Service.entity.Order;
import com.ben.Order.Service.model.dto.OrderDto;
import com.ben.Order.Service.model.response.PaymentResponse;
import com.ben.Order.Service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createOrder(OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrdersByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(orderService.getOrdersByBranchId(branchId));
    }


}
