package com.ben.Inventory.Service.repo;

import com.ben.Inventory.Service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category[] findCategoriesByStoreId(Long storeId);
}
