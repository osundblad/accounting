package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.persistence.BookDataService;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class BookRestFacade {

    @NotNull
    private final BookDataService bookDataService;

    @Autowired
    public BookRestFacade(@NotNull final BookDataService bookDataService) {
        this.bookDataService = bookDataService;
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
        return bookDataService.create(bookYear);
    }

    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookDataService.getAllBookYears();
    }

    @NotNull
    public Stream<BookYear> getAllBookYears(@NotNull final UUID bookId) {
        return bookDataService.getAllBookYears(bookId);
    }

}
