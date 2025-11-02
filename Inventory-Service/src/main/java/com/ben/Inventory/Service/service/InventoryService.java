package com.ben.Inventory.Service.service;

import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.model.dto.BranchDto;
import com.ben.Inventory.Service.model.dto.InventoryDto;
import com.ben.Inventory.Service.model.dto.ProductDto;

import java.util.List;

public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto addProductToInventory(Long branchDto, Product productDto);

    InventoryDto updateInventory(InventoryDto inventoryDto, Long inventoryId);

    void deleteInventory(Long inventoryId);

    InventoryDto getInventory(Long inventoryId);

    InventoryDto getInventoryByProductId(Long productId);

    List<InventoryDto> getBranchInventory(Long branchId);
}
