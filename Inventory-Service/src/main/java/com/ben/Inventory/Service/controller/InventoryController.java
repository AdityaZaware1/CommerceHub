package com.ben.Inventory.Service.controller;

import com.ben.Inventory.Service.model.dto.InventoryDto;
import com.ben.Inventory.Service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<InventoryDto> createInventory(
            @RequestBody InventoryDto inventoryDto) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<InventoryDto> updateInventory(
            @RequestBody InventoryDto inventoryDto,
            @PathVariable Long inventoryId) {
        return ResponseEntity.ok(inventoryService.updateInventory(inventoryDto, inventoryId));
    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<?> deleteInventory(
            @PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{inventoryId}")
    public ResponseEntity<InventoryDto> getInventory(
            @PathVariable Long inventoryId) {
        return ResponseEntity.ok(inventoryService.getInventory(inventoryId));
    }

    @GetMapping("/getByProductId/{id}")
    public ResponseEntity<InventoryDto> getInventoryByProductId(
            @PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(id));
    }

    @GetMapping("/getByBranchId/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranchId(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(inventoryService.getBranchInventory(branchId));
    }

//    @PostMapping("/addProductToInventory")
//    public ResponseEntity<InventoryDto> addProductToInventory(InventoryDto inventoryDto) {
//        return ResponseEntity.ok(inventoryService.addProductToInventory(inventoryDto.getBranchId(), inventoryDto.getProduct()));
//    }

}
