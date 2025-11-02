package com.ben.Order.Service.kafka;

import com.ben.Order.Service.enums.OrderStatus;
import com.ben.Order.Service.model.dto.OrderDto;
import com.ben.Order.Service.repo.OrderRepo;
import com.ben.Order.Service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final OrderService orderService;
    private final OrderRepo orderRepo;

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void orderConfirmation(String orderId) {

        Long oId = Long.parseLong(orderId);
        OrderDto orderDto = orderService.getOrderById(oId);
        orderDto.setStatus(OrderStatus.COMPLETED);
        orderService.confirmOrder(oId, orderDto);
    }
}
