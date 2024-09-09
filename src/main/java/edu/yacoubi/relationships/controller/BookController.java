package edu.yacoubi.relationships.controller;


import edu.yacoubi.relationships.model.dto.request.BookRequestDto;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.service.IBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;

    @PostMapping()
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookData) {
        return new ResponseEntity<>(bookService.addBook(bookData), HttpStatus.CREATED);
    }

    @Tag(name = "get book", description = "GET methods to Book Endpoint")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable final Long id) {
        BookResponseDto bookResponseDto = bookService.getBookDetails(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @Tag(name = "get book", description = "GET methods to Book Endpoint")
    @GetMapping()
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> bookResponseDtoList = bookService.getAllBooks();
        return new ResponseEntity<>(bookResponseDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable final Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @RequestBody final BookRequestDto bookRequestDto,
            @PathVariable final Long id) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PutMapping("/associate/{bookId}/authors/{authorId}")
    public ResponseEntity<BookResponseDto> associateAuthorWithBook(
            @PathVariable final Long bookId,
            @PathVariable final Long authorId) {
        BookResponseDto bookResponseDto = bookService.associateAuthorWithBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PutMapping("/disassociate/{bookId}/authors/{authorId}")
    public ResponseEntity<BookResponseDto> disassociateAuthorFromBook(
            @PathVariable final Long bookId,
            @PathVariable final Long authorId) {
        BookResponseDto bookResponseDto = bookService.disassociateAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PutMapping("/associate/{bookId}/categories/{categoryId}")
    public ResponseEntity<BookResponseDto> associateCategoryWithBook(
            @PathVariable final Long bookId,
            @PathVariable final Long categoryId) {
        BookResponseDto bookResponseDto = bookService.associateCategoryWithBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PutMapping("/disassociate/{bookId}/categories/{categoryId}")
    public ResponseEntity<BookResponseDto> disassociateCategoryFromBook(
            @PathVariable final Long bookId,
            @PathVariable final Long categoryId) {
        BookResponseDto bookResponseDto = bookService.disassociateCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
}
