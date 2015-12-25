package se.eris.accounting.persistence.jpa.model;

import org.junit.Test;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JpaBookTest {

    @Test
    public void new_toCore_roundtrip() {
        final Book book = new Book(BookId.of(UUID.randomUUID()), "name", "description");

        assertThat(new JpaBook(book).toCore(), is(book));
    }

    @Test
    public void new_shouldSetId() {
        final Book book = new Book(BookId.empty(), "name", "description");

        assertTrue(new JpaBook(book).toCore().getId().isPresent());
    }

}