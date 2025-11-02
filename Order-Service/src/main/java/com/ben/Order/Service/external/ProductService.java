package com.ben.Order.Service.external;


import com.ben.Order.Service.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Inventory-Service", url = "${product.service.url}")
public interface ProductService {

    @GetMapping("/branch/{branchId}")
    public List<ProductDto> getProductsByBranchId(@PathVariable Long branchId);

    @GetMapping("/get/{productId}")
    public ProductDto getProductById(@PathVariable Long productId);

    @GetMapping("/{product}/{storeId}")
    public ProductDto getProductByStoreId(@PathVariable Long product, @PathVariable Long storeId);
}
