package com.ben.Inventory.Service.mapper;

import com.ben.Inventory.Service.entity.Category;
import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.model.dto.CategoryDto;
import com.ben.Inventory.Service.model.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toProductDto(Product product, CategoryDto category) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(category)
                .brand(product.getBrand())
                .sellingPrice(product.getSellingPrice())
                .storeId(product.getStoreId())
                .quantity(product.getQuantity())
                .branchId(product.getBranchId())
                .build();
    }

    public static Product toProduct(ProductDto productDto, Category category) {
        return Product.builder()
                .id(productDto.getId())
                .category(category)
                .name(productDto.getName())
                .price(productDto.getPrice())
                .brand(productDto.getBrand())
                .quantity(productDto.getQuantity())
                .sellingPrice(productDto.getSellingPrice())
                .storeId(productDto.getStoreId())
                .branchId(productDto.getBranchId())
                .build();
    }
}
