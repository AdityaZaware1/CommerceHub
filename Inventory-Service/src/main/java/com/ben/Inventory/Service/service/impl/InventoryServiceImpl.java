package com.ben.Inventory.Service.service.impl;

import com.ben.Inventory.Service.entity.Inventory;
import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.enums.UserRole;
import com.ben.Inventory.Service.external.BranchService;
import com.ben.Inventory.Service.external.UserService;
import com.ben.Inventory.Service.mapper.InventoryMapper;
import com.ben.Inventory.Service.model.dto.BranchDto;
import com.ben.Inventory.Service.model.dto.InventoryDto;
import com.ben.Inventory.Service.model.dto.UserDto;
import com.ben.Inventory.Service.repo.InventoryRepo;
import com.ben.Inventory.Service.repo.ProductRepo;
import com.ben.Inventory.Service.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final BranchService branchService;
    private final ProductRepo productRepo;
    private final UserService userService;
    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {

        UserDto userDto = getCurrentUser();

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin");
        }

        BranchDto branchDto = getBranch(inventoryDto.getBranchId());
        Product product = productRepo.findById(inventoryDto.getProduct()).orElse(null);

        Inventory inventory = InventoryMapper.toEntity(inventoryDto, branchDto.getId(), product);

        inventoryRepo.save(inventory);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto addProductToInventory(Long branchDto, Product productDto) {

        UserDto userDto = getCurrentUser();

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin");
        }

        Inventory inventory = inventoryRepo.findByBranchId(branchDto);
        //inventory.getProduct().add(productDto);

        inventoryRepo.save(inventory);
        return null;
    }

    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto, Long inventoryId) {
        UserDto userDto = getCurrentUser();

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin");
        }

        Inventory inventory = inventoryRepo.findById(inventoryId).orElse(null);

        inventoryRepo.save(inventory);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        UserDto userDto = getCurrentUser();

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin");
        }

        Inventory inventory = inventoryRepo.findById(inventoryId).orElse(null);
        inventoryRepo.delete(inventory);
    }

    @Override
    public InventoryDto getInventory(Long inventoryId) {

        Inventory inventory = inventoryRepo.findById(inventoryId).orElse(null);

        if (inventory == null) {
            throw new RuntimeException("Inventory not found");
        }
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductId(Long productId) {

        Inventory inventory;

//        if (inventory != null) {
//            return InventoryMapper.toDto(inventory);
//        }
        return null;
    }

    @Override
    public List<InventoryDto> getBranchInventory(Long branchId) {

        List<InventoryDto> inventories = new ArrayList<>();

        for (Inventory inventory : inventoryRepo.findInventoriesByBranchId(branchId)) {
            inventories.add(InventoryMapper.toDto(inventory));
        }

        return inventories;
    }

    @CircuitBreaker(name =  "userService", fallbackMethod = "UserFallBacks")
    public UserDto getCurrentUser() {

        UserDto userDto = userService.getCurrentUser();

        if(userDto.getId() == 0L) {
            throw new RuntimeException("User Service is not available");
        }

        return userDto;
    }

    public UserDto UserFallBacks() {
        return new UserDto(0L, "unavilable", null, null, null);
    }

    @CircuitBreaker(name = "branchService", fallbackMethod = "BranchFallBacks")
    public BranchDto getBranch(Long branchId) {
        BranchDto branchDto = branchService.getBranch(branchId);

        if(branchDto.getId() == 0L) {
            throw new RuntimeException("Branch Service is not available");
        }
        return branchDto;
    }

    public BranchDto BranchFallBacks(Long branchId) {
        return new BranchDto(0L, "unavilable", null, null, null, null,null,null,null,null,null);
    }
}
