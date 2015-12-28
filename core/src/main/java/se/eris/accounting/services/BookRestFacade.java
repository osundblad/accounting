package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.persistence.BookDataService;

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

    public void delete(@NotNull final BookId bookId) {
        bookDataService.delete(bookId);
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
    public Stream<BookYear> getAllBookYears(@NotNull final BookId bookId) {
        return bookDataService.getAllBookYears(bookId);
    }

    @NotNull
    public Stream<BookYearAccount> getBookYearAccounts(@NotNull final BookYearId bookYearId) {
        return bookDataService.getBookYearAccounts(bookYearId);
    }

}
