package com.ben.Inventory.Service.service.impl;

import com.ben.Inventory.Service.entity.Category;
import com.ben.Inventory.Service.external.StoreService;
import com.ben.Inventory.Service.mapper.CategoryMapper;
import com.ben.Inventory.Service.model.dto.CategoryDto;
import com.ben.Inventory.Service.model.dto.StoreDto;
import com.ben.Inventory.Service.repo.CategoryRepo;
import com.ben.Inventory.Service.service.CategoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final StoreService storeService;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        StoreDto storeDto = getStore(categoryDto.getStoreId());

        if (storeDto.equals(null)) {
            throw new RuntimeException("Store not found");
        }

        Category category = CategoryMapper.toEntity(categoryDto);
        categoryRepo.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getCategoriesByStoreId(Long storeId) {

        List<CategoryDto> categories = new ArrayList<>();

        for(Category category : categoryRepo.findCategoriesByStoreId((storeId))) {
            categories.add(CategoryMapper.toDto(category));
        }

        return categories;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category = categoryRepo.findById(id).orElse(null);

        if (category != null) {
            return CategoryMapper.toDto(category);
        }

        return null;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

        Category category = CategoryMapper.toEntity(categoryDto);
        categoryRepo.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepo.deleteById(id);
    }


    @CircuitBreaker(name = "storeService", fallbackMethod = "StoreFallBackMethod")
    public StoreDto getStore(Long storeId) {
        StoreDto storeDto = storeService.getStore(storeId);

        if(storeDto.getStoreName().equals("unavilable")) {
            throw new RuntimeException("Store Service in not available");
        }

        return storeDto;
    }


    public StoreDto StoreFallBackMethod(Long storeId) {
        return new StoreDto(null, "unavilable", null, null,null,null);
    }
}
