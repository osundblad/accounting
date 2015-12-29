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

import java.util.stream.Stream;

@Service
@Transactional
public class BookDataServiceJpa implements BookDataService {

    @NotNull
    private final BookDao bookDao;
    @NotNull
    private final BookYearDao bookYearDao;
    @NotNull
    private final BookYearAccountDao bookYearAccountDao;

    @Autowired
    public BookDataServiceJpa(@NotNull final BookDao bookDao, @NotNull final BookYearDao bookYearDao, @NotNull final BookYearAccountDao bookYearAccountDao) {
        this.bookDao = bookDao;
        this.bookYearDao = bookYearDao;
        this.bookYearAccountDao = bookYearAccountDao;
    }

    @Override
    @NotNull
    public Stream<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @NotNull
    public Book create(@NotNull final Book book) {
        return bookDao.create(book);
    }

    @Override
    public void delete(@NotNull final BookId bookId) {
        // todo validate that book has no years
        bookDao.delete(bookId);
    }

    @Override
    @NotNull
    public Stream<BookYear> getBookYears(@NotNull final BookId bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @Override
    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        // todo validate year continuity and overlap
        return bookYearDao.create(bookYear);
    }

    @Override
    public void delete(@NotNull final BookYearId bookYearId) {
        bookYearDao.delete(bookYearId);
    }

    @NotNull
    @Override
    public Stream<BookYearAccount> findBookYearAccounts(@NotNull final BookYearId bookYearId) {
        return bookYearAccountDao.findBookYearAccounts(bookYearId);
    }

    @NotNull
    @Override
    public BookYearAccount create(@NotNull final BookYearAccount account) {
        return bookYearAccountDao.create(account);
    }

    @Override
    public void delete(@NotNull final BookYearAccountId bookYearAccountId) {
        bookYearAccountDao.delete(bookYearAccountId);
    }

    @NotNull
    @Override
    public Stream<Transaction> getTransactions(@NotNull final BookYearId bookYearId) {
        // todo
        return Stream.empty();
    }

    @NotNull
    @Override
    public Transaction create(@NotNull final Transaction transaction) {
        // todo
        return null;
    }

}
