package com.ben.Inventory.Service.service;

import com.ben.Inventory.Service.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, Long user);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto, Long user);

    void deleteProduct(Long productId);

    ProductDto getProductByStoreIdAndName(Long storeId, String name);

    List<ProductDto> getProductsByStoreId(Long storeId);

    List<ProductDto> getProductsByBranchId(Long branchId);

    List<ProductDto> searchProducts(Long storeId,String query);
}
