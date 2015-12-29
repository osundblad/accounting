package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Transaction;

import java.util.stream.Stream;

public interface BookDataService {

    @NotNull
    Stream<Book> getAllBooks();

    @NotNull
    Book create(@NotNull Book book);

    void delete(@NotNull BookId bookId);

    @NotNull
    Stream<BookYear> getBookYears(@NotNull BookId bookId);

    @NotNull
    BookYear create(@NotNull BookYear bookYear);

    void delete(@NotNull BookYearId bookYearId);

    @NotNull
    Stream<BookYearAccount> findBookYearAccounts(@NotNull BookYearId bookYearId);

    @NotNull
    BookYearAccount create(@NotNull BookYearAccount account);

    void delete(@NotNull BookYearAccountId bookYearAccountId);

    @NotNull
    Stream<Transaction> getTransactions(@NotNull BookYearId bookYearId);
}
