package com.ben.Inventory.Service.mapper;


import com.ben.Inventory.Service.entity.Inventory;
import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.model.dto.BranchDto;
import com.ben.Inventory.Service.model.dto.InventoryDto;
import com.ben.Inventory.Service.model.dto.ProductDto;

public class InventoryMapper {

    public static InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                //.product(productDto)
                .build();
    }

    public static Inventory toEntity(InventoryDto inventoryDto, Long branchId, Product product) {
        return Inventory.builder()
                .id(inventoryDto.getId())
                .branchId(branchId)
                .product(product)
                .build();
    }
}
