package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.AuthorRequestDto;
import edu.yacoubi.relationships.model.dto.response.AuthorResponseDto;
import edu.yacoubi.relationships.service.IAuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final IAuthorService authorService;

    @PostMapping()
    public ResponseEntity<AuthorResponseDto> addAuthor(AuthorRequestDto authorRequestDto) {
        return new ResponseEntity<>(authorService.addAuthor(authorRequestDto), HttpStatus.CREATED);
    }

    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorDetails(@PathVariable final Long id) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorDetails(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping()
    public ResponseEntity<List<AuthorResponseDto>> getAuthors() {
        List<AuthorResponseDto> authorResponseDtoList = authorService.getAllAuthors();
        return new ResponseEntity<>(authorResponseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable final Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable final Long id,
            AuthorRequestDto authorRequestDto) {
        AuthorResponseDto updatedAuthorResponseDto =
                authorService.updateAuthor(id, authorRequestDto);
        return new ResponseEntity<>(updatedAuthorResponseDto, HttpStatus.OK);
    }

    @PutMapping("/associateZipcode/{authorId}/{zipcodeId}")
    public ResponseEntity<AuthorResponseDto> associateZipcodeWithAuthor(
            @PathVariable final Long authorId,
            @PathVariable final Long zipcodeId) {
        AuthorResponseDto associatedAuthorResponseDto =
                authorService.associateZipcodeWithAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(associatedAuthorResponseDto, HttpStatus.OK);
    }

    @PutMapping("/disassociateZipcode/{authorId}")
    public ResponseEntity<AuthorResponseDto> disassociateZipcodeFromAuthor(
            @PathVariable final Long authorId) {
        AuthorResponseDto disassociatedAuthorResponseDto =
                authorService.disassociateZipcodeFromAuthor(authorId);
        return new ResponseEntity<>(disassociatedAuthorResponseDto, HttpStatus.OK);
    }
}
