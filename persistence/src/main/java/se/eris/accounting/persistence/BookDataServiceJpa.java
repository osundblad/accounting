package se.eris.accounting.persistence;

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
    public BookDataServiceJpa(final BookDao bookDao, final BookYearDao bookYearDao, final BookYearAccountDao bookYearAccountDao, final TransactionDao transactionDao) {
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
        public Book create(final Book book) {
        return bookDao.create(book);
    }

    @Override
        public Book update(final Book book) {
        return bookDao.update(book);
    }

    @Override
    public void delete(final BookId bookId) {
        // todo validate that book has no years
        bookDao.delete(bookId);
    }

    @Override
        public Stream<BookYear> getBookYears(final BookId bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @Override
        public BookYear create(final BookYear bookYear) {
        // todo validate year continuity and overlap
        return bookYearDao.create(bookYear);
    }

    @Override
    public void delete(final BookYearId bookYearId) {
        bookYearDao.delete(bookYearId);
    }

        @Override
    public Stream<BookYearAccount> findBookYearAccounts(final BookYearId bookYearId) {
        return bookYearAccountDao.findBookYearAccounts(bookYearId);
    }

        @Override
    public BookYearAccount create(final BookYearAccount account) {
        return bookYearAccountDao.create(account);
    }

    @Override
    public void delete(final BookYearAccountId bookYearAccountId) {
        bookYearAccountDao.delete(bookYearAccountId);
    }

        @Override
    public Stream<Transaction> getTransactions(final BookYearId bookYearId) {
        return transactionDao.findBookYearTransactions(bookYearId);
    }

        @Override
    public Transaction create(final Transaction transaction) {
        return transactionDao.create(transaction);
    }

        @Override
    public Transaction get(final TransactionId transactionId) {
        return transactionDao.get(transactionId);
    }

    @Override
    public void delete(final TransactionId transactionId) {
        transactionDao.delete(transactionId);
    }

}
