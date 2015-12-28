package se.eris.accounting.model.book.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountCodeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_validCodes() {
        AccountCode.from("1");
        AccountCode.from("1234567890");
        AccountCode.from("abcdefghijkl");
        AccountCode.from("MNOPQRSTUVWX");
    }

    @Test
    public void of_empty_isInvalid() {
        exception.expect(IllegalArgumentException.class);
        AccountCode.from("");
    }

    @Test
    public void of_withIllegalCharacter_isInvalid() {
        exception.expect(IllegalArgumentException.class);
        AccountCode.from("a c");
    }

}