package com.ben.Order.Service.external.resillence4j;

import com.ben.Order.Service.external.ProductService;
import com.ben.Order.Service.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductFallBack implements ProductService {

    @Override
    public List<ProductDto> getProductsByBranchId(Long branchId) {
        return new ArrayList<>();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return new ProductDto();
    }

    @Override
    public ProductDto getProductByStoreId(Long product, Long storeId) {
        return new ProductDto();
    }
}
