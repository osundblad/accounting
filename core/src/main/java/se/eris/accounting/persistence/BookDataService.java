package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;

import java.util.stream.Stream;

public interface BookDataService {

    @NotNull
    Stream<Book> getAllBooks();

    @NotNull
    Book create(@NotNull Book book);

    void delete(@NotNull BookId bookId);

    @NotNull
    Stream<BookYear> getAllBookYears();

    @NotNull
    Stream<BookYear> getAllBookYears(@NotNull BookId bookId);

    @NotNull
    BookYear create(@NotNull BookYear bookYear);

    @NotNull
    Stream<BookYearAccount> getBookYearAccounts(@NotNull BookYearId bookYearId);

}
