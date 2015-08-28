package se.eris.accounting.persistence.jpa.model;

import org.junit.Test;
import se.eris.accounting.model.Book;
import se.eris.accounting.model.BookYear;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookTest {

    @Test
    public void new_toCore_roundtrip() {
        Book book = new Book(UUID.randomUUID(), "name", "description");

        assertThat(new JpaBook(book).toCore(), is(book));
    }

    @Test
    public void new_shouldSetId() {
        Book book = new Book(null, "name", "description");

        assertThat(new JpaBook(book).toCore().getId(), not(nullValue()));
    }

}