package se.eris.accounting.persistence.jpa.model;

import org.junit.Test;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookName;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JpaBookTest {

    @Test
    public void new_toCore_roundtrip() {
        final Book book = new Book(Optional.of(BookId.random()), BookName.of("name"), BookDescription.of("description"));

        assertThat(new JpaBook(book).toCore(), is(book));
    }

    @Test
    public void new_shouldSetId() {
        final Book book = new Book(Optional.empty(), BookName.of("name"), BookDescription.of("description"));

        assertThat(new JpaBook(book).toCore().getId().isPresent(), is(true));
    }

}