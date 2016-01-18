package se.eris.accounting.web.rest.model;

import org.junit.Test;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookName;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RestBookTest {

    @Test
    public void roundtrip() {
        final Book book = new Book(Optional.empty(), BookName.of("name"), BookDescription.of("dexc"));

        assertThat(new RestBook(book).toCore(), is(book));
    }

    @Test
    public void getBookId() {
        final RestBook restBook = new RestBook(null, "name", "dexc");

        assertThat(restBook.getBookId(), is(Optional.empty()));
    }
}