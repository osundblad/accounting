package se.eris.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringByteLengthLimitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_inRange() {
        StringByteLengthLimit.of(2, 4).validate("12");
        StringByteLengthLimit.of(2, 4).validate("ö");
        StringByteLengthLimit.max(4).validate("öö");
    }

    @Test
    public void validate_toShort() {
        exception.expect(IllegalArgumentException.class);
        StringByteLengthLimit.of(2, 4).validate("1");
    }

    @Test
    public void validate_toLong() {
        exception.expect(IllegalArgumentException.class);
        StringByteLengthLimit.max(4).validate("aåä");
    }

}