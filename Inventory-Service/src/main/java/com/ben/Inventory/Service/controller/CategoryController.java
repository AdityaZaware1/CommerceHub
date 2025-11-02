package com.ben.Inventory.Service.controller;

import com.ben.Inventory.Service.model.dto.CategoryDto;
import com.ben.Inventory.Service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public CategoryDto createCategory(
            @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PostMapping("/update/{id}")
    public CategoryDto updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @PostMapping("/delete/{id}")
    public void deleteCategory(
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @PostMapping("/get/{id}")
    public CategoryDto getCategoryById(
            @PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/list/{storeId}")
    public List<CategoryDto> getCategoriesByStoreId(
            @PathVariable Long storeId) {
        return categoryService.getCategoriesByStoreId(storeId);
    }


}
