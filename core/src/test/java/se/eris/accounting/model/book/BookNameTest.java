package se.eris.accounting.model.book;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.jtype.StringTestUtil;

public class BookNameTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_valid() {
        BookName.of("a");
        BookName.of("name");
        BookName.of(StringTestUtil.createLongString(BookName.MAX_LENGTH));
    }

    @Test
    public void of_toShort() {
        exception.expect(IllegalArgumentException.class);
        BookName.of("");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        BookName.of(StringTestUtil.createLongString(BookName.MAX_LENGTH + 1));
    }

}