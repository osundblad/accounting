package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.*;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class BookDataServiceJpa implements BookDataService {

    @NotNull
    private final BookDao bookDao;
    @NotNull
    private final BookYearDao bookYearDao;

    @Autowired
    public BookDataServiceJpa(@NotNull final BookDao bookDao, @NotNull final BookYearDao bookYearDao) {
        this.bookDao = bookDao;
        this.bookYearDao = bookYearDao;
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
        bookDao.delete(bookId);
    }

    @Override
    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDao.findAllBookYears();
    }

    @Override
    @NotNull
    public Stream<BookYear> getAllBookYears(@NotNull final BookId bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @Override
    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDao.create(bookYear);
    }

    @NotNull
    @Override
    public Stream<BookYearAccount> getBookYearAccounts(@NotNull final BookYearId bookYearId) {
        // todo
        final BookYearAccount account1 = new BookYearAccount(Optional.empty(), bookYearId, AccountInfo.of(AccountCode.of("1437"), AccountName.of("Avanza Sparkonto"), AccountDescription.of("Bas kontot hos Avanza Bank")));
        final BookYearAccount account2 = new BookYearAccount(Optional.empty(), bookYearId, AccountInfo.of(AccountCode.of("1438"), AccountName.of("Avanza Depå"), AccountDescription.of("Depå kontot hos Avanza Bank")));
        return Stream.of(account1, account2);
    }

}
