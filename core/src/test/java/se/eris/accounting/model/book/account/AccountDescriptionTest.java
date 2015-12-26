package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.util.StringTestUtil;

public class AccountDescriptionTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_valid() {
        AccountDescription.of("");
        AccountDescription.of("This is a text with some 5p€c!@l characters. <=**=> ");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        AccountDescription.of(StringTestUtil.createLongString(AccountDescription.MAX_LENGTH + 1));
    }

}