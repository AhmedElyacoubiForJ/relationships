package edu.yacoubi.relationships.mapper.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.response.CategoryResponseDto;
import edu.yacoubi.relationships.model.entity.Book;
import edu.yacoubi.relationships.model.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapperImpl implements IMapper<Category, CategoryResponseDto> {
    @Override
    public CategoryResponseDto mapTo(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getBooks().stream()
                        .map(Book::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<CategoryResponseDto> mapTo(List<Category> categories) {
        return categories.stream().map(this::mapTo).collect(Collectors.toList());
    }

    @Override
    public Category mapFrom(CategoryResponseDto categoryResponseDto) {
        return null;
    }
}
