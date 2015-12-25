package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.DatePeriod;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookYearTest {

    @Test
    public void new_toCore_roundtrip() {
        final BookYear bookYear = createBookYear(UUID.randomUUID());

        assertThat(new JpaBookYear(bookYear).toCore(), is(bookYear));
    }

    @Test
    public void new_shouldSetId() {
        final JpaBookYear jpaBookYear = new JpaBookYear(createBookYear(null));

        assertThat(jpaBookYear.toCore().getId().isPresent(), is(true));
    }

    @NotNull
    private BookYear createBookYear(@Nullable final UUID id) {
        final LocalDate now = LocalDate.now();
        return new BookYear(Optional.ofNullable(id), BookId.random(), DatePeriod.between(now, now.plusDays(2)));
    }

}