package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.request.BookRequestDto;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.model.entity.Book;
import edu.yacoubi.relationships.model.entity.Category;
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
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

        return mapper.mapTo(bookRepository.save(newBook));
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("No book found with id: " + bookId)
        );
    }

    @Override
    public BookResponseDto getBookDetails(Long bookId) {
        return mapper.mapTo(getBook(bookId));
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return mapper.mapTo(
                StreamSupport.stream(
                        bookRepository.findAll().spliterator(),
                        false
                ).collect(Collectors.toList())
        );
    }

    @Override
    public void deleteBook(Long bookId) {
        log.info("Deleting book with id: {}", bookId);
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("No book found with id: " + bookId);
        }
        bookRepository.deleteById(bookId);
        log.info("Deleted book with id: {}", bookId);
    }

    @Transactional
    @Override
    public BookResponseDto updateBook(Long bookId, BookRequestDto bookData) {
        Book bookToUpdate = getBook(bookId);
        bookToUpdate.setName(bookData.getName());
        if (!bookData.getAuthorIds().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookData.getAuthorIds()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToUpdate.setAuthors(authors);
        }
        if (bookData.getCategoryId() != null) {
            bookToUpdate.setCategory(categoryService.getCategory(bookData.getCategoryId()));
        }

        return mapper.mapTo(bookRepository.save(bookToUpdate));
    }

    @Override
    public BookResponseDto associateAuthorWithBook(Long bookId, Long authorId) {
        Book theBook = getBook(bookId);
        Author authorToAssociate = authorService.getAuthor(authorId);
        if (theBook.getAuthors().contains(theBook)) {
            throw new IllegalArgumentException("Author is already associated with this book");
        }
        theBook.addAuthor(authorToAssociate);
        authorToAssociate.addBook(theBook);

        return mapper.mapTo(bookRepository.save(theBook));
    }

    @Override
    public BookResponseDto disassociateAuthorFromBook(Long bookId, Long authorId) {
        Book theBook = getBook(bookId);
        Author authorToDisassociate = authorService.getAuthor(authorId);
        if (!theBook.getAuthors().contains(authorToDisassociate)) {
            throw new IllegalArgumentException("Author is not associated with this book");
        }
        theBook.deleteAuthor(authorToDisassociate);
        authorToDisassociate.removeBook(theBook);

        return mapper.mapTo(bookRepository.save(theBook));
    }

    @Override
    public BookResponseDto associateCategoryWithBook(Long bookId, Long categoryId) {
        Book theBook = getBook(bookId);
        Category categoryToAssociate = categoryService.getCategory(categoryId);
        if (Objects.nonNull(theBook.getCategory())) {
            throw new IllegalArgumentException("Category is already associated with this book");
        }
        theBook.setCategory(categoryToAssociate);
        categoryToAssociate.addBook(theBook);

        return mapper.mapTo(bookRepository.save(theBook));
    }

    @Override
    public BookResponseDto disassociateCategoryFromBook(Long bookId, Long categoryId) {
        Book theBook = getBook(bookId);
        Category categoryToDisassociate = categoryService.getCategory(categoryId);
        if (Objects.isNull(theBook.getCategory()) || !theBook.getCategory().equals(categoryToDisassociate)) {
            throw new IllegalArgumentException("Category is not associated with this book");
        }
        theBook.setCategory(null);
        categoryToDisassociate.removeBook(theBook);

        return mapper.mapTo(bookRepository.save(theBook));
    }
}
