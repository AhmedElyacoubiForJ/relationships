package edu.yacoubi.relationships.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipcodeName;

    public AuthorResponseDto(Long id, String name, List<String> bookNames) {
        this.id = id;
        this.name = name;
        this.bookNames = bookNames;
    }
}
