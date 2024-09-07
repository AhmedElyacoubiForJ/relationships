package edu.yacoubi.relationships.mapper.impl;

import edu.yacoubi.relationships.mapper.IMapper;
import edu.yacoubi.relationships.model.dto.response.BookResponseDto;
import edu.yacoubi.relationships.model.entity.Author;
import edu.yacoubi.relationships.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements IMapper<Book, BookResponseDto> {
    @Override
    public BookResponseDto mapTo(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getCategory().getName(),
                book.getAuthors().stream()
                        .map(Author::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<BookResponseDto> mapTo(List<Book> books) {
        return books.stream().map(this::mapTo).collect(Collectors.toList());
    }

    @Override
    public Book mapFrom(BookResponseDto bookResponseDto) {
        // TODO: Implement mapping from BookResponseDto to Book
        return null;
    }
}
