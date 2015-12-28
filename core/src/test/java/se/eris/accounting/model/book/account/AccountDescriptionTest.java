package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.util.StringTestUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountDescriptionTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void empty() {
        assertThat(AccountDescription.empty().asString(), is(""));
    }

    @Test
    public void of_valid() {
        AccountDescription.from("");
        AccountDescription.from("This is a text with some 5p€c!@l characters. <=**=> ");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        AccountDescription.from(StringTestUtil.createLongString(AccountDescription.MAX_LENGTH + 1));
    }

}