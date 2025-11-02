package com.ben.Inventory.Service.service;

import com.ben.Inventory.Service.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getCategoriesByStoreId(Long storeId);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);
}
