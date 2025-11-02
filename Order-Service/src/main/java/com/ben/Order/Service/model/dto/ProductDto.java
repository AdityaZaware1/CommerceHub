package com.ben.Order.Service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private String quantity;
    private String description;
    private Double price;
    private Double sellingPrice;
    private Double brand;
    private CategoryDto category;
    private Long storeId;
}
