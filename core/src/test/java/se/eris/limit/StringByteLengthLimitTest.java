package se.eris.limit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.charset.StandardCharsets;

public class StringByteLengthLimitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_inRange() {
        StringByteLengthLimit.of(2, 4).validate("12");
        StringByteLengthLimit.of(2, 4).validate("1234");
        StringByteLengthLimit.of(2, 2).validate("ö");
        StringByteLengthLimit.zeroTo(4).validate("öö");
    }

    @Test
    public void validate_toShort() {
        exception.expect(IllegalArgumentException.class);
        StringByteLengthLimit.of(2, 4).validate("1");
    }

    @Test
    public void validate_toLong() {
        exception.expect(IllegalArgumentException.class);
        StringByteLengthLimit.zeroTo((1+2+2) - 1).validate("aåä");
    }

    @Test
    public void validate_utf16_toLong() {
        exception.expect(IllegalArgumentException.class);
        StringByteLengthLimit.of(0, (4+2+2) - 1, StandardCharsets.UTF_16).validate("abc");
    }

}