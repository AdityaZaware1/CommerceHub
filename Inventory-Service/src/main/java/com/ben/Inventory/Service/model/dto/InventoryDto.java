package com.ben.Inventory.Service.model.dto;

import com.ben.Inventory.Service.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {

    private Long id;
    private Long branchId;
    private Long product;

    private Integer quantity;
}
