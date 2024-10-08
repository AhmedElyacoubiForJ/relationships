package edu.yacoubi.relationships.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String name;
    private List<Long> authorIds;
    private Long categoryId;
}
