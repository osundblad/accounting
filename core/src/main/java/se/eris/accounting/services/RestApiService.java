package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.Book;
import se.eris.accounting.model.BookYear;
import se.eris.accounting.persistence.BookDao;
import se.eris.accounting.persistence.BookYearDao;

import java.util.stream.Stream;

@Service
public class RestApiService {

    private final BookYearDao bookYearDao;
    private final BookDao bookDao;

    @Autowired
    public RestApiService(@NotNull final BookDao bookDao, @NotNull final BookYearDao bookYearDao) {
        this.bookDao = bookDao;
        this.bookYearDao = bookYearDao;
    }

    @NotNull
    public Book create(@NotNull final Book book) {
        return bookDao.create(book);
    }

    @NotNull
    public Stream<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDao.create(bookYear);
    }

    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDao.getAllBookYears();
    }

    public Stream<BookYear> getAllBookYears(final long bookId) {
        return bookYearDao.getAllBookYears(bookId);
    }
}
