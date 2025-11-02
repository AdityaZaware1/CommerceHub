package com.ben.Inventory.Service.mapper;


import com.ben.Inventory.Service.entity.Category;
import com.ben.Inventory.Service.model.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
