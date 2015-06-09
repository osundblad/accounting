package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.BookYear;
import se.eris.accounting.persistence.jpa.JpaBookYearRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

import java.util.stream.Stream;

@Service
public class BookYearDataService {

    private final JpaBookYearRepository bookYearRepository;

    @Autowired
    public BookYearDataService(@NotNull final JpaBookYearRepository bookYearRepository) {
        this.bookYearRepository = bookYearRepository;
    }

    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearRepository.findAll().stream().map(JpaBookYear::toCore);
    }

    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        if (bookYear.hasId()) {
            throw new AlreadyPersistedException(bookYear + " has already been persisted (use update)." );
        }
        return bookYearRepository.save(new JpaBookYear(bookYear)).toCore();
    }

}
