package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.account.AccountInfo;
import se.eris.accounting.model.book.account.BookYearAccount;

import java.util.Optional;
import java.util.UUID;
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
    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDao.findAllBookYears();
    }

    @Override
    @NotNull
    public Stream<BookYear> getAllBookYears(@NotNull final UUID bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @Override
    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDao.create(bookYear);
    }

    @NotNull
    @Override
    public Stream<BookYearAccount> getBookYearAccounts(@NotNull final UUID bookYearId) {
        // todo
        final BookYearAccount account1 = new BookYearAccount(Optional.<UUID>empty(), bookYearId, new AccountInfo("1437", "Avanza Sparkonto", "Bas kontot hos Avanza Bank"));
        final BookYearAccount account2 = new BookYearAccount(Optional.<UUID>empty(), bookYearId, new AccountInfo("1438", "Avanza Depå", "Depå kontot hos Avanza Bank"));
        return Stream.of(account1, account2);
    }

}
