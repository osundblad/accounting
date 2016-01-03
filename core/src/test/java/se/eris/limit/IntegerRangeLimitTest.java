package se.eris.limit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegerRangeLimitTest {

    @Test
    public void validate_inRange() {
        IntegerRangeLimit.of(2, 7).validate(2);
        IntegerRangeLimit.of(2, 7).validate(7);
    }

    @Test
    public void validate_toLow() {
        assertThat(IntegerRangeLimit.of(2, 7).validate(1).hasMessages(), is(true));
    }

    @Test
    public void validate_toHigh() {
        assertThat(IntegerRangeLimit.zeroTo(7).validate(8).hasMessages(), is(true));
    }

}