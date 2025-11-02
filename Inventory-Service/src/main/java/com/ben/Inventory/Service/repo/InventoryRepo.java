package com.ben.Inventory.Service.repo;

import com.ben.Inventory.Service.entity.Inventory;
import com.ben.Inventory.Service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByBranchId(Long branchId);

    List<Inventory> findInventoriesByBranchId(Long branchId);

    //Inventory findByProduct(List<Product> product);
}
