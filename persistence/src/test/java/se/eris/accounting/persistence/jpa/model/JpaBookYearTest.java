package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.type.OpenDatePeriod;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookYearTest {

    @Test
    public void new_toCore_roundtrip() {
        final BookYear bookYear = createBookYear(Optional.of(BookYearId.random()));

        assertThat(new JpaBookYear(bookYear).toCore(), is(bookYear));
    }

    @Test
    public void new_shouldSetId() {
        final JpaBookYear jpaBookYear = new JpaBookYear(createBookYear(Optional.empty()));

        assertThat(jpaBookYear.toCore().getId().isPresent(), is(true));
    }

    @NotNull
    private BookYear createBookYear(@NotNull final Optional<BookYearId> id) {
        final LocalDate now = LocalDate.now();
        return new BookYear(id, BookId.random(), OpenDatePeriod.between(now, now.plusDays(2)));
    }

}