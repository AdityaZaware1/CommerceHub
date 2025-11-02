package com.ben.Inventory.Service.controller;

import com.ben.Inventory.Service.model.dto.ProductDto;
import com.ben.Inventory.Service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/{user}")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            @PathVariable Long user) {
        return ResponseEntity.ok(productService.createProduct(productDto, user));
    }

    @PutMapping("/update/{productId}/{user}")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestBody ProductDto productDto,
            @PathVariable Long productId,
            @PathVariable Long user) {
        return ResponseEntity.ok(productService.updateProduct(productId, productDto, user));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{product}/{storeId}")
    public ResponseEntity<ProductDto> getProductByStoreId(@PathVariable Long product, @PathVariable Long storeId) {
        return ResponseEntity.ok(productService.getProductById(product));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getProductsByStoreId(@PathVariable Long storeId) {
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }

    @GetMapping("/search/{storeId}/{query}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable Long storeId, @PathVariable String query) {
        return ResponseEntity.ok(productService.searchProducts(storeId, query));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ProductDto>> getProductsByBranchId(@PathVariable Long branchId) {
        return ResponseEntity.ok(productService.getProductsByBranchId(branchId));
    }
}
