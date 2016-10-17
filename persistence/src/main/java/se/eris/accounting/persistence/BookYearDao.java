package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.persistence.jpa.JpaBookYearRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

import java.util.stream.Stream;

@Service
public class BookYearDao {

        private final JpaBookYearRepository bookYearRepository;

    @Autowired
    public BookYearDao(@NotNull final JpaBookYearRepository bookYearRepository) {
        this.bookYearRepository = bookYearRepository;
    }

        public Stream<BookYear> findAllBookYears() {
        return bookYearRepository.findAll().stream().map(JpaBookYear::toCore);
    }

        public Stream<BookYear> findAllBookYears(@NotNull final BookId bookId) {
        return bookYearRepository.findByBookId(bookId.asString()).stream().map(JpaBookYear::toCore);
    }

        public BookYear create(@NotNull final BookYear bookYear) {
        if (bookYear.getId().isPresent()) {
            throw new AlreadyPersistedException(bookYear + " has already been persisted (use update)." );
        }
        return bookYearRepository.save(new JpaBookYear(bookYear)).toCore();
    }

    public void delete(@NotNull final BookYearId bookYearId) {
        bookYearRepository.delete(bookYearId.asString());
    }

}
