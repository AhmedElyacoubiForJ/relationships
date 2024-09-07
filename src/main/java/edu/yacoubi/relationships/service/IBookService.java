package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.BookRequestDto;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.model.entity.Book;

import java.util.List;

public interface IBookService {
    BookResponseDto addBook(BookRequestDto bookData);

    BookResponseDto getBookDetails(Long bookId);

    Book getBook(Long bookId);

    List<BookResponseDto> getAllBooks();

    void deleteBook(Long bookId);

    BookResponseDto updateBook(Long bookId, BookRequestDto bookData);

    BookResponseDto associateAuthorWithBook(Long bookId, Long authorId);

    BookResponseDto disassociateAuthorFromBook(Long bookId, Long authorId);

    BookResponseDto associateCategoryWithBook(Long bookId, Long categoryId);

    BookResponseDto disassociateCategoryFromBook(Long bookId, Long categoryId);
}
