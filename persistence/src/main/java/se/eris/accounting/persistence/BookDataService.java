package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.Book;
import se.eris.accounting.model.BookYear;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class BookDataService {

    private final BookDao bookDao;
    private final BookYearDao bookYearDao;

    @Autowired
    public BookDataService(@NotNull final BookDao bookDao, @NotNull final BookYearDao bookYearDao) {
        this.bookDao = bookDao;
        this.bookYearDao = bookYearDao;
    }

    @NotNull
    public Stream<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @NotNull
    public Book create(@NotNull final Book book) {
        return bookDao.create(book);
    }


    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDao.findAllBookYears();
    }

    @NotNull
    public Stream<BookYear> getAllBookYears(@NotNull final UUID bookId) {
        return bookYearDao.findAllBookYears(bookId);
    }

    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDao.create(bookYear);
    }

}
