package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.util.StringTestUtil;

public class AccountNameTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_valid() {
        AccountName.from("a");
        AccountName.from(StringTestUtil.createLongString(AccountName.MAX_LENGTH));
    }

    @Test
    public void of_trim() {
        AccountName.from(" \r" + StringTestUtil.createLongString(AccountName.MAX_LENGTH) + "  \n");
    }

    @Test
    public void of_toShort() {
        exception.expect(IllegalArgumentException.class);
        AccountName.from("");
    }

    @Test
    public void of_toShortWhiteSpace() {
        exception.expect(IllegalArgumentException.class);
        AccountName.from("  ");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        AccountName.from(StringTestUtil.createLongString(AccountName.MAX_LENGTH + 1));
    }

}