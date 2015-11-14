package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.Book;
import se.eris.accounting.model.BookYear;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Describe class/interface here.
 */
public interface BookDataService {
    @NotNull
    Stream<Book> getAllBooks();

    @NotNull
    Book create(@NotNull Book book);

    @NotNull
    Stream<BookYear> getAllBookYears();

    @NotNull
    Stream<BookYear> getAllBookYears(@NotNull UUID bookId);

    @NotNull
    BookYear create(@NotNull BookYear bookYear);
}
