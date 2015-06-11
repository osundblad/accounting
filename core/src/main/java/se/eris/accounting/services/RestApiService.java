package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.Book;
import se.eris.accounting.model.BookYear;
import se.eris.accounting.persistence.BookDataService;
import se.eris.accounting.persistence.BookYearDataService;

import java.util.stream.Stream;

@Service
public class RestApiService {

    private final BookYearDataService bookYearDataService;
    private final BookDataService bookDataService;

    @Autowired
    public RestApiService(@NotNull final BookDataService bookDataService, @NotNull final BookYearDataService bookYearDataService) {
        this.bookDataService = bookDataService;
        this.bookYearDataService = bookYearDataService;
    }

    @NotNull
    public Book create(@NotNull final Book book) {
        return bookDataService.create(book);
    }

    @NotNull
    public Stream<Book> getAllBooks() {
        return bookDataService.getAllBooks();
    }

    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDataService.create(bookYear);
    }

    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDataService.getAllBookYears();
    }

    public Stream<BookYear> getAllBookYears(final long bookId) {
        return bookYearDataService.getAllBookYears(bookId);
    }
}
