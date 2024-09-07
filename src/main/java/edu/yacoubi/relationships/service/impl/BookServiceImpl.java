package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.request.BookRequestDto;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.model.entity.Book;
import edu.yacoubi.relationships.repository.BookRepository;
import edu.yacoubi.relationships.service.IAuthorService;
import edu.yacoubi.relationships.service.IBookService;
import edu.yacoubi.relationships.service.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IMapper<Book, BookResponseDto> mapper;

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookData) {
        if (bookData.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("you need to provide at least one author for the new book");
        }
        if (bookData.getCategoryId() == null) {
            throw new IllegalArgumentException("you need to provide a category for the new book");
        }

        Book newBook = new Book();
        newBook.setName(bookData.getName());
        List<Author> authors = new ArrayList<>();
        for (Long authorId : bookData.getAuthorIds()) {
            Author author = authorService.getAuthor(authorId);
            authors.add(author);
        }
        newBook.setAuthors(authors);
        newBook.setCategory(categoryService.getCategory(bookData.getCategoryId()));

        Book savedBook = bookRepository.save(newBook);


        return mapper.mapTo(savedBook);
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
