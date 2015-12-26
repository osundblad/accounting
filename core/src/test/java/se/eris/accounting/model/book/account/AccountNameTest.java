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
        AccountName.of("a");
        AccountName.of(StringTestUtil.createLongString(AccountName.MAX_LENGTH));
    }

    @Test
    public void of_trim() {
        AccountName.of(" \r" + StringTestUtil.createLongString(AccountName.MAX_LENGTH) + "  \n");
    }

    @Test
    public void of_toShort() {
        exception.expect(IllegalArgumentException.class);
        AccountName.of("");
    }

    @Test
    public void of_toShortWhiteSpace() {
        exception.expect(IllegalArgumentException.class);
        AccountName.of("  ");
    }

    @Test
    public void of_toLong() {
        exception.expect(IllegalArgumentException.class);
        AccountName.of(StringTestUtil.createLongString(AccountName.MAX_LENGTH + 1));
    }

}