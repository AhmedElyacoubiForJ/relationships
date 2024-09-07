package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.AuthorRequestDto;
import edu.yacoubi.relationships.model.dto.response.AuthorResponseDto;
import edu.yacoubi.relationships.model.entity.Author;

import java.util.List;

public interface IAuthorService {
    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorDetails(Long authorId);

    Author getAuthor(Long authorId);

    AuthorResponseDto updateAuthor(Long authorId, AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long authorId);

    AuthorResponseDto associateZipcodeWithAuthor(Long authorId, Long zipcodeId);

    AuthorResponseDto disassociateZipcodeFromAuthor(Long authorId);
}
