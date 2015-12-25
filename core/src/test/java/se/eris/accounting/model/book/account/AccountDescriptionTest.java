package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        final StringBuilder sb = new StringBuilder(2000);
        while (sb.length() <= AccountDescription.MAX_LENGTH) {
            sb.append("some more text in description ");
        }

        exception.expect(IllegalArgumentException.class);
        AccountDescription.of(sb.toString());
    }

}