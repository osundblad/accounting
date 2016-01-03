package se.eris.limit;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringByteLengthLimitTest {

    @Test
    public void validate_inRange() {
        StringByteLengthLimit.of(2, 4).validate("12");
        StringByteLengthLimit.of(2, 4).validate("1234");
        StringByteLengthLimit.of(2, 2).validate("ö");
        StringByteLengthLimit.zeroTo(4).validate("öö");
    }

    @Test
    public void validate_toShort() {
        assertThat(StringByteLengthLimit.of(2, 4).validate("1").hasMessages(), is(true));
    }

    @Test
    public void validate_toLong() {
        assertThat(StringByteLengthLimit.zeroTo((1+2+2) - 1).validate("aåä").hasMessages(), is(true));
    }

    @Test
    public void validate_utf16_toLong() {
        assertThat(StringByteLengthLimit.of(0, (4+2+2) - 1, StandardCharsets.UTF_16).validate("abc").hasMessages(), is(true));
    }

}