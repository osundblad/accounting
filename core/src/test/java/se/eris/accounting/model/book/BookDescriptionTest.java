package se.eris.accounting.model.book;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.util.StringTestUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookDescriptionTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void empty() {
        assertThat(BookDescription.empty().asString(), is(""));
    }

    @Test
    public void of_valid() {
        BookDescription.of("");
        BookDescription.of("This is a text with some 5p€c!@l characters. <=**=> ");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        BookDescription.of(StringTestUtil.createLongString(BookDescription.MAX_LENGTH + 1));

    }
}