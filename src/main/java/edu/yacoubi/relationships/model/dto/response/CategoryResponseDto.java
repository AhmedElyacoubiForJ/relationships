package edu.yacoubi.relationships.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<String> bookNames;

    public CategoryResponseDto(Long id, String name, List<String> bookNames) {
        this.id = id;
        this.name = name;
        this.bookNames = bookNames;
    }
}
