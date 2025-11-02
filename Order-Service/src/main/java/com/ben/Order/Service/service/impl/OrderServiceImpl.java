package com.ben.Order.Service.service.impl;

import com.ben.Order.Service.entity.Order;
import com.ben.Order.Service.entity.OrderItem;
import com.ben.Order.Service.external.OrderPaymentService;
import com.ben.Order.Service.external.ProductService;
import com.ben.Order.Service.external.UserService;
import com.ben.Order.Service.mapper.OrderItemMapper;
import com.ben.Order.Service.mapper.OrderMapper;
import com.ben.Order.Service.model.dto.*;
import com.ben.Order.Service.model.response.PaymentResponse;
import com.ben.Order.Service.repo.OrderRepo;
import com.ben.Order.Service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final UserService userService;
    private final OrderPaymentService orderPaymentService;
    @Override
    public PaymentResponse createOrder(OrderDto orderDto) {

        List<ProductDto> productDto = getProductsByBranchId(orderDto.getBranchId());

        //ProductDto productDto1 = productService.getProductByStoreId(productDto.get(0).getId(), orderDto.getBranchId());

        List<OrderItemDto> orderItems = orderDto.getItems();

        for (OrderItemDto orderItemDto : orderItems) {

            for (ProductDto product : productDto) {
                if (!product.getId().equals(orderItemDto.getProductId())) {
                    throw new RuntimeException("Product not found");
                }
            }
        }

        if (productDto == null) {
            throw new RuntimeException("Product not found");
        }

        UserDto userDto = getUserById(orderDto.getUserId());

        if (userDto == null) {
            throw new RuntimeException("User not found");
        }

        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderItemDto orderItemDto : orderItems) {
            OrderItem orderItem = OrderItemMapper.toEntity(orderItemDto);
            orderItemList.add(orderItem);
        }

        Order order = OrderMapper.toEntity(orderDto, orderItemList);
        orderRepo.save(order);
        OrderMapper.toDto(order, orderItems);

        PaymentDto paymentDto = PaymentDto.builder()
                .amount(order.getAmount())
                .orderId(order.getId())
                .userId(userDto.getId())
                .build();

        PaymentResponse paymentResponse = orderPaymentService.createPayment(paymentDto);
        return paymentResponse;
    }

    @Override
    public OrderDto getOrderById(Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return OrderMapper.toDto(order);
    }

    @Override
    public OrderDto confirmOrder(Long orderId, OrderDto orderDto) {

        OrderDto orderDto1 = getOrderById(orderId);

        if(orderDto1 == null) {
            throw new RuntimeException("Order not found");
        }
        Order order = OrderMapper.toEntity(orderDto1);
        orderRepo.save(order);

        return OrderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepo.findByUserId(userId);

        if(orders == null) {
            throw new RuntimeException("Order not found");
        }

        List<OrderDto> orderDtos = new ArrayList<>();

        for(Order order : orders) {
            OrderDto orderDto = OrderMapper.toDto(order);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrdersByBranchId(Long branchId) {

        List<Order> orders = orderRepo.findByBranchId(branchId);
        if(orders == null) {
            throw new RuntimeException("Order not found");
        }

        List<OrderDto> orderDtos = new ArrayList<>();

        for(Order order : orders) {
            OrderDto orderDto = OrderMapper.toDto(order);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBacks")
    public UserDto getUserById(Long userId) {
        UserDto userDto = userService.getUserById(userId);

        if(userDto.getId() == 0L) {
            throw new RuntimeException("User Service is not available");
        }
        return userDto;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "ProductFallBacks")
    public List<ProductDto> getProductsByBranchId(Long branchId) {

        return productService.getProductsByBranchId(branchId);
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "PaymentFallBacks")
    public PaymentResponse createPayment(PaymentDto paymentDto) {
        PaymentResponse paymentResponse=  orderPaymentService.createPayment(paymentDto);

        if(paymentResponse.getPaymentUrl().equals("Payment Failed")) {
            throw new RuntimeException("Payment Service is not available");
        }
        return paymentResponse;
    }

    public PaymentResponse PaymentFallBacks(PaymentDto paymentDto) {

        return new PaymentResponse("Payment Failed", null);
    }

    public UserDto UserFallBacks(Long userId) {

        return new UserDto(0L, "unavilable", null, null, null);
    }

    public List<ProductDto> ProductFallBacks(Long branchId) {
        return new ArrayList<>();
    }

}
