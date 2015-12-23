package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.persistence.jpa.JpaBookYearRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class BookYearDao {

    @NotNull
    private final JpaBookYearRepository bookYearRepository;

    @Autowired
    public BookYearDao(@NotNull final JpaBookYearRepository bookYearRepository) {
        this.bookYearRepository = bookYearRepository;
    }

    @NotNull
    public Stream<BookYear> findAllBookYears() {
        return bookYearRepository.findAll().stream().map(JpaBookYear::toCore);
    }

    @NotNull
    public Stream<BookYear> findAllBookYears(@NotNull final UUID bookId) {
        return bookYearRepository.findByBookId(bookId.toString()).stream().map(JpaBookYear::toCore);
    }

    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        if (bookYear.getId().isPresent()) {
            throw new AlreadyPersistedException(bookYear + " has already been persisted (use update)." );
        }
        return bookYearRepository.save(new JpaBookYear(bookYear)).toCore();
    }

}
