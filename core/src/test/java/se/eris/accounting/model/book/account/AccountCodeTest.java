package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountCodeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_validCodes() {
        AccountCode.of("1");
        AccountCode.of("1234567890");
        AccountCode.of("abcdefghijkl");
        AccountCode.of("MNOPQRSTUVWX");
    }

    @Test
    public void of_empty_isInvalid() {
        exception.expect(IllegalArgumentException.class);
        AccountCode.of("");
    }

    @Test
    public void of_withIllegalCharacter_isInvalid() {
        exception.expect(IllegalArgumentException.class);
        AccountCode.of("a c");
    }

}