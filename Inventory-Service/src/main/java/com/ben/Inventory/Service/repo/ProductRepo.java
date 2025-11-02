package com.ben.Inventory.Service.repo;

import com.ben.Inventory.Service.entity.Product;
import com.ben.Inventory.Service.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);

    List<Product> findByBranchId(Long branchId);

    List<Product> searchProductByStoreIdAndName(Long storeId, String name);

    Product findProductByStoreIdAndName(Long storeId, String name);
}
