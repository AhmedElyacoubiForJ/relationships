package edu.yacoubi.relationships.mapper.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.response.AuthorResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapperImpl implements IMapper<Author, AuthorResponseDto> {
    @Override
    public AuthorResponseDto mapTo(Author author) {
        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getBooks().stream()
                        .map(Book::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<AuthorResponseDto> mapTo(List<Author> authors) {
        return authors.stream().map(this::mapTo).collect(Collectors.toList());
    }

    @Override
    public Author mapFrom(AuthorResponseDto authorResponseDto) {
        return null;
    }
}
