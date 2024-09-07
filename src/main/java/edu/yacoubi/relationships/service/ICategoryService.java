package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.CategoryRequestDto;
import edu.yacoubi.relationships.model.dto.response.CategoryResponseDto;
import edu.yacoubi.relationships.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategory(Long categoryId);

    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategoryDetails(Long categoryId);

    List<CategoryResponseDto> getAllCategories();

    void deleteCategory(Long categoryId);

    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryData);

}
