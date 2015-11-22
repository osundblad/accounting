package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import se.eris.accounting.model.book.BookYear;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookYearTest {

    @Test
    public void new_toCore_roundtrip() {
        BookYear bookYear = createBookYear(UUID.randomUUID());

        assertThat(new JpaBookYear(bookYear).toCore(), is(bookYear));
    }

    @Test
    public void new_shouldSetId() {
        JpaBookYear jpaBookYear = new JpaBookYear(createBookYear(null));

        assertThat(jpaBookYear.toCore().getId(), not(nullValue()));
    }

    @NotNull
    private BookYear createBookYear(@Nullable final UUID id) {
        LocalDate now = LocalDate.now();
        return new BookYear(id, UUID.randomUUID(), now, now.plusDays(2));
    }

}