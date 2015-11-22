package se.eris.accounting.persistence.jpa.model;

import org.junit.Test;
import se.eris.accounting.model.book.Book;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookTest {

    @Test
    public void new_toCore_roundtrip() {
        final Book book = new Book(Optional.of(UUID.randomUUID()), "name", "description");

        assertThat(new JpaBook(book).toCore(), is(book));
    }

    @Test
    public void new_shouldSetId() {
        final Book book = new Book(Optional.empty(), "name", "description");

        assertThat(new JpaBook(book).toCore().getId(), not(nullValue()));
    }

}