package com.ben.Order.Service.model.dto;

import com.ben.Order.Service.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Long id;
    private Integer quantity;
    private Double price;
    private Long productId;
    private OrderDto order;

}
