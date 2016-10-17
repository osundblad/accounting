package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;

import java.util.stream.Stream;

public interface BookDataService {

        Stream<Book> getAllBooks();

        Book create(@NotNull Book book);

        Book update(@NotNull Book book);

    void delete(@NotNull BookId bookId);

        Stream<BookYear> getBookYears(@NotNull BookId bookId);

        BookYear create(@NotNull BookYear bookYear);

    void delete(@NotNull BookYearId bookYearId);

        Stream<BookYearAccount> findBookYearAccounts(@NotNull BookYearId bookYearId);

        BookYearAccount create(@NotNull BookYearAccount account);

    void delete(@NotNull BookYearAccountId bookYearAccountId);

        Stream<Transaction> getTransactions(@NotNull BookYearId bookYearId);

        Transaction create(@NotNull Transaction transaction);

        Transaction get(@NotNull TransactionId transactionId);

    void delete(@NotNull TransactionId transactionId);
}
