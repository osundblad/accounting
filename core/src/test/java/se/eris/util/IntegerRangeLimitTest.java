package se.eris.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IntegerRangeLimitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_inRange() {
        IntegerRangeLimit.of(2, 7).validate(2);
        IntegerRangeLimit.of(2, 7).validate(7);
    }

    @Test
    public void validate_toLow() {
        exception.expect(IllegalArgumentException.class);
        IntegerRangeLimit.of(2, 7).validate(1);
    }

    @Test
    public void validate_toHigh() {
        exception.expect(IllegalArgumentException.class);
        IntegerRangeLimit.max(7).validate(8);
    }

}