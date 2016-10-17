package se.eris.accounting.persistence;

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

        Book create(Book book);

        Book update(Book book);

    void delete(BookId bookId);

        Stream<BookYear> getBookYears(BookId bookId);

        BookYear create(BookYear bookYear);

    void delete(BookYearId bookYearId);

        Stream<BookYearAccount> findBookYearAccounts(BookYearId bookYearId);

        BookYearAccount create(BookYearAccount account);

    void delete(BookYearAccountId bookYearAccountId);

        Stream<Transaction> getTransactions(BookYearId bookYearId);

        Transaction create(Transaction transaction);

        Transaction get(TransactionId transactionId);

    void delete(TransactionId transactionId);
}
