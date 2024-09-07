package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.model.dto.request.BookRequestDto;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.model.entity.Book;
import edu.yacoubi.relationships.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    @Override
    public BookResponseDto addBook(BookRequestDto bookData) {
        return null;
    }

    @Override
    public BookResponseDto getBookDetails(Long bookId) {
        return null;
    }

    @Override
    public Book getBook(Long bookId) {
        return null;
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return List.of();
    }

    @Override
    public void deleteBook(Long bookId) {

    }

    @Override
    public BookResponseDto updateBook(Long bookId, BookRequestDto bookData) {
        return null;
    }

    @Override
    public BookResponseDto associateAuthorWithBook(Long bookId, Long authorId) {
        return null;
    }

    @Override
    public BookResponseDto disassociateAuthorFromBook(Long bookId, Long authorId) {
        return null;
    }

    @Override
    public BookResponseDto associateCategoryWithBook(Long bookId, Long categoryId) {
        return null;
    }

    @Override
    public BookResponseDto disassociateCategoryFromBook(Long bookId, Long categoryId) {
        return null;
    }
}
