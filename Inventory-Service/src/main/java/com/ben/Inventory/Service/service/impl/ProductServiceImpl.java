package com.ben.Inventory.Service.service.impl;

import com.ben.Inventory.Service.entity.Category;
import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.enums.UserRole;
import com.ben.Inventory.Service.external.StoreService;
import com.ben.Inventory.Service.external.UserService;
import com.ben.Inventory.Service.mapper.CategoryMapper;
import com.ben.Inventory.Service.mapper.ProductMapper;
import com.ben.Inventory.Service.model.dto.ProductDto;
import com.ben.Inventory.Service.model.dto.StoreDto;
import com.ben.Inventory.Service.model.dto.UserDto;
import com.ben.Inventory.Service.repo.ProductRepo;
import com.ben.Inventory.Service.service.InventoryService;
import com.ben.Inventory.Service.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final UserService userService;
    private  final StoreService storeService;
    private final InventoryService inventoryService;

    @Override
    public ProductDto createProduct(ProductDto productDto, Long user) {

        UserDto userDto = getUserById(user);

        if(userDto == null ) {
            return null;
        }

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin or store manager");
        }

        Category category = CategoryMapper.toEntity(productDto.getCategory());

        Product product = ProductMapper.toProduct(productDto, category);

        productRepo.save(product);
        inventoryService.addProductToInventory(productDto.getBranchId(), product);
        return ProductMapper.toProductDto(product, CategoryMapper.toDto(category));
    }

    @Override
    public ProductDto getProductById(Long productId) {

        Product product = productRepo.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        return ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory()));
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto, Long user) {

        UserDto userDto = getUserById(user);

        if(userDto == null ) {
            return null;
        }

        if (userDto.getRole() != UserRole.STORE_ADMIN || userDto.getRole() != UserRole.STORE_MANAGER) {
            throw new RuntimeException("User is not a store admin or store manager");
        }

        Product product = productRepo.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(CategoryMapper.toEntity(productDto.getCategory()));

        productRepo.save(product);
        inventoryService.addProductToInventory(productDto.getBranchId(), product);

        return ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory()));
    }

    @Override
    public void deleteProduct(Long productId) {

        Product product = productRepo.findById(productId).orElse(null);

        if (product == null) {
            return;
        }

        productRepo.delete(product);
    }

    @Override
    public ProductDto getProductByStoreIdAndName(Long storeId, String name) {

        Product product = productRepo.findProductByStoreIdAndName(storeId, name);

        if (product != null) {
            return ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory()));
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : productRepo.findByStoreId(storeId)) {
            productDtoList.add(ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory())));
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByBranchId(Long branchId) {

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : productRepo.findByBranchId(branchId)) {
            productDtoList.add(ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory())));
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> searchProducts(Long storeId, String query) {

        List<ProductDto> productDtos = new ArrayList<>();

        StoreDto storeDto = storeService.getStore(storeId);

        if (storeDto == null) {
            return null;
        }

        for(Product product : productRepo.searchProductByStoreIdAndName(storeId, query)) {
            productDtos.add(ProductMapper.toProductDto(product, CategoryMapper.toDto(product.getCategory())));
        }

        return productDtos;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "UserFallBacks")
    public UserDto getUserById(Long userId) {
        UserDto userDto = userService.getUserById(userId);

        if(userDto.getId() == 0L) {
            throw new RuntimeException("User Service is not available");
        }

        return userDto;
    }

    public UserDto UserFallBacks(Long userId) {
        return new UserDto(0L, "unavilable", null, null, null);
    }

    @CircuitBreaker(name = "storeService", fallbackMethod = "StoreFallBacks")
    public StoreDto getStore(Long storeId) {
        StoreDto storeDto = storeService.getStore(storeId);

        if(storeDto.getId() == 0L) {
            throw new RuntimeException("Store Service is not available");
        }

        return storeDto;
    }

    public StoreDto StoreFallBacks(Long storeId) {
        return new StoreDto(null, "unavilable", null, null,null,null);
    }
}
