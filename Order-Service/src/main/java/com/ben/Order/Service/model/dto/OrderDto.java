package com.ben.Order.Service.model.dto;

import com.ben.Order.Service.entity.OrderItem;
import com.ben.Order.Service.enums.OrderStatus;
import com.ben.Order.Service.enums.PaymentType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Long amount;

    private Long branchId;

    private Long userId;
    private PaymentType paymentType;
    private List<OrderItemDto> items;
    private OrderStatus status;
}
