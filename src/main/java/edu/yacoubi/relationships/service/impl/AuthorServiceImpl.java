package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.model.dto.request.AuthorRequestDto;
import edu.yacoubi.relationships.model.dto.response.AuthorResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.service.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements IAuthorService {
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public List<AuthorRequestDto> getAllAuthors() {
        return List.of();
    }

    @Override
    public AuthorResponseDto getAuthorDetails(Long authorId) {
        return null;
    }

    @Override
    public Author getAuthor(Long authorId) {
        return null;
    }

    @Override
    public Author updateAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId) {

    }

    @Override
    public AuthorResponseDto associateZipcodeWithAuthor(Long authorId, Long zipcodeId) {
        return null;
    }

    @Override
    public AuthorResponseDto disassociateZipcodeFromAuthor(Long authorId) {
        return null;
    }
}
