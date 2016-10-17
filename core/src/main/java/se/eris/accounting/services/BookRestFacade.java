package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.persistence.BookDataService;

import java.util.stream.Stream;

@Service
public class BookRestFacade {

        private final BookDataService bookDataService;

    @Autowired
    public BookRestFacade(@NotNull final BookDataService bookDataService) {
        this.bookDataService = bookDataService;
    }

        public Book create(@NotNull final Book book) {
        return bookDataService.create(book);
    }

    public void delete(@NotNull final BookId bookId) {
        bookDataService.delete(bookId);
    }

        public Stream<Book> getAllBooks() {
        return bookDataService.getAllBooks();
    }

        public BookYear create(@NotNull final BookYear bookYear) {
        return bookDataService.create(bookYear);
    }

    public void delete(@NotNull final BookYearId bookYearId) {
        bookDataService.delete(bookYearId);
    }

        public Stream<BookYear> getBookYears(@NotNull final BookId bookId) {
        return bookDataService.getBookYears(bookId);
    }

        public Stream<BookYearAccount> getBookYearAccounts(@NotNull final BookYearId bookYearId) {
        return bookDataService.findBookYearAccounts(bookYearId);
    }

        public BookYearAccount create(@NotNull final BookYearAccount account) {
        return bookDataService.create(account);
    }

    public void delete(@NotNull final BookYearAccountId bookYearAccountId) {
        bookDataService.delete(bookYearAccountId);
    }

        public Stream<Transaction> getTransactions(@NotNull final BookYearId bookYearId) {
        return bookDataService.getTransactions(bookYearId);
    }

        public Transaction create(@NotNull final Transaction transaction) {
        return bookDataService.create(transaction);
    }

        public Transaction get(@NotNull final TransactionId transactionId) {
        return bookDataService.get(transactionId);
    }

    public void delete(@NotNull final TransactionId transactionId) {
        bookDataService.delete(transactionId);
    }
}
