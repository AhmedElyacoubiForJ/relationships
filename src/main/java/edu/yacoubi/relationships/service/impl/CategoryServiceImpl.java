package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.request.CategoryRequestDto;
import edu.yacoubi.relationships.model.dto.response.CategoryResponseDto;
import edu.yacoubi.relationships.model.entity.Category;
import edu.yacoubi.relationships.repository.CategoryRepository;
import edu.yacoubi.relationships.service.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private IMapper<Category, CategoryResponseDto> mapper;

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("Category not found with id: " + categoryId)
        );
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category newCategory = new Category(categoryRequestDto.getName());
        Category savedCategory = categoryRepository.save(newCategory);
        return mapper.mapTo(savedCategory);
    }

    @Override
    public CategoryResponseDto getCategoryDetails(Long categoryId) {
        return mapper.mapTo(getCategory(categoryId));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return mapper.mapTo(
                StreamSupport.stream(
                                categoryRepository.findAll().spliterator(),
                                false)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.info("Deleting category with id: {}", categoryId);
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
        log.info("Deleted category with id: {}", categoryId);
    }

    @Transactional
    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryData) {
        Category existingCategory = getCategory(categoryId);
        existingCategory.setName(categoryData.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapper.mapTo(updatedCategory);
    }
}
