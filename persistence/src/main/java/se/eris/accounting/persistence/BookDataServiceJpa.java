package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;

import java.util.stream.Stream;

@SuppressWarnings("unused")
@Service
@Transactional
public class BookDataServiceJpa implements BookDataService {

        private final BookDao bookDao;
        private final BookYearDao bookYearDao;
        private final BookYearAccountDao bookYearAccountDao;
        private final TransactionDao transactionDao;

    @Autowired
    public BookDataServiceJpa(@NotNull final BookDao bookDao, @NotNull final BookYearDao bookYearDao, @NotNull final BookYearAccountDao bookYearAccountDao, @NotNull final TransactionDao transactionDao) {
        this.bookDao = bookDao;
        this.bookYearDao = bookYearDao;
        this.bookYearAccountDao = bookYearAccountDao;
        this.transactionDao = transactionDao;
    }

    @Override
        public Stream<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
        public Book create(@NotNull final Book book) {
        return bookDao.create(book);
    }

    @Override
        public Book update(@NotNull final Book book) {
        return bookDao.update(book);
    }

    @Override
    public void delete(@NotNull final BookId bookId) {
        // todo validate that book has no years
        bookDao.delete(bookId);
    }

    @Override
        public Stream<BookYear> getBookYears(@NotNull final BookId bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @Override
        public BookYear create(@NotNull final BookYear bookYear) {
        // todo validate year continuity and overlap
        return bookYearDao.create(bookYear);
    }

    @Override
    public void delete(@NotNull final BookYearId bookYearId) {
        bookYearDao.delete(bookYearId);
    }

        @Override
    public Stream<BookYearAccount> findBookYearAccounts(@NotNull final BookYearId bookYearId) {
        return bookYearAccountDao.findBookYearAccounts(bookYearId);
    }

        @Override
    public BookYearAccount create(@NotNull final BookYearAccount account) {
        return bookYearAccountDao.create(account);
    }

    @Override
    public void delete(@NotNull final BookYearAccountId bookYearAccountId) {
        bookYearAccountDao.delete(bookYearAccountId);
    }

        @Override
    public Stream<Transaction> getTransactions(@NotNull final BookYearId bookYearId) {
        return transactionDao.findBookYearTransactions(bookYearId);
    }

        @Override
    public Transaction create(@NotNull final Transaction transaction) {
        return transactionDao.create(transaction);
    }

        @Override
    public Transaction get(@NotNull final TransactionId transactionId) {
        return transactionDao.get(transactionId);
    }

    @Override
    public void delete(@NotNull final TransactionId transactionId) {
        transactionDao.delete(transactionId);
    }

}
