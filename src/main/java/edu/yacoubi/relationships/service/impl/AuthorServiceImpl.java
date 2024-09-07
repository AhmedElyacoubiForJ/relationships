package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.request.AuthorRequestDto;
import edu.yacoubi.relationships.model.dto.response.AuthorResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.model.entity.Zipcode;
import edu.yacoubi.relationships.repository.AuthorRepository;
import edu.yacoubi.relationships.service.IAuthorService;
import edu.yacoubi.relationships.service.IZipcodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;
    private final IZipcodeService zipcodeService;
    private final IMapper<Author, AuthorResponseDto> mapper;

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorData) {
        if (authorData.getZipcodeId() == null) {
            throw new IllegalArgumentException("Zipcode id is required");
        }
        Author newAuthor = new Author();
        newAuthor.setName(authorData.getName());
        Zipcode existingZipcode = zipcodeService.getZipcode(authorData.getZipcodeId());
        newAuthor.setZipcode(existingZipcode);

        return mapper.mapTo(authorRepository.save(newAuthor));
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return mapper.mapTo(
                StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public AuthorResponseDto getAuthorDetails(Long authorId) {
        return mapper.mapTo(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Author with id [%d] could not be found", authorId)
                )
        );
    }

    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author existingAuthor = getAuthor(authorId);
        existingAuthor.setName(authorRequestDto.getName());

        if (authorRequestDto.getZipcodeId() != null) {
            Zipcode existingZipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            existingAuthor.setZipcode(existingZipcode);
        }
        return mapper.mapTo(authorRepository.save(existingAuthor));
    }

    @Override
    public void deleteAuthor(Long authorId) {
        boolean existsById = authorRepository.existsById(authorId);
        if (!existsById) {
            throw new IllegalArgumentException(
                    String.format("Author with id [%d] could not be found", authorId)
            );
        }
        authorRepository.deleteById(authorId);
    }

    @Transactional
    @Override
    public AuthorResponseDto associateZipcodeWithAuthor(Long authorId, Long zipcodeId) {
        Author existingAuthor = getAuthor(authorId);
        Zipcode existingZipcode = zipcodeService.getZipcode(zipcodeId);
        if (Objects.nonNull(existingAuthor.getZipcode())) {
            throw new RuntimeException(
                    String.format("Author with id [%d] already has a zipcode assigned", authorId)
            );
        }
        existingAuthor.setZipcode(existingZipcode);
        return mapper.mapTo(authorRepository.save(existingAuthor));
    }

    @Transactional
    @Override
    public AuthorResponseDto disassociateZipcodeFromAuthor(Long authorId) {
        Author existingAuthor = getAuthor(authorId);
        existingAuthor.setZipcode(null);
        return mapper.mapTo(authorRepository.save(existingAuthor));
    }
}
