package edu.yacoubi.relationships.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDto {
    private Long id;
    private String name;
    private List<String> authorNames;
    private String categoryName;

    public BookResponseDto(Long id, String categoryName, List<String> authorNames) {
        this.id = id;
        this.categoryName = categoryName;
        this.authorNames = authorNames;
    }
}
