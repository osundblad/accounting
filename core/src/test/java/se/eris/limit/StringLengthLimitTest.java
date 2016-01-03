package se.eris.limit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringLengthLimitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_minHigherThanMax() {
        exception.expect(IllegalArgumentException.class);
        StringLengthLimit.of(4, 1);
    }

    @Test
    public void of_minNegative() {
        exception.expect(IllegalArgumentException.class);
        StringLengthLimit.of(-4, 1);
    }

    @Test
    public void max_allowsEmptyString() {
        StringLengthLimit.zeroTo(8).validate("");
    }

    @Test
    public void max_inRange() {
        StringLengthLimit.zeroTo(3).validate("123");
    }

    @Test
    public void validate_inRange() {
        StringLengthLimit.of(2, 4).validate("12");
        StringLengthLimit.of(2, 4).validate("1234");
    }

    @Test
    public void validate_toShort() {
        assertThat(StringLengthLimit.of(2, 4).validate("1").hasMessages(), is(true));
    }

    @Test
    public void validate_toLong() {
        assertThat(StringLengthLimit.of(2, 4).validate("12345").hasMessages(), is(true));
    }

}