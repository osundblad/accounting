package se.eris.accounting.model.book.transaction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AmountTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_partLessThan0_shouldFail() {
        exception.expect(IllegalArgumentException.class);
        Amount.of(1, -1);
    }

    @Test
    public void of_partGreaterThan99_shouldFail() {
        exception.expect(IllegalArgumentException.class);
        Amount.of(1, 100);
    }

    @Test
    public void isZero() {
        assertTrue(Amount.ZERO.isZero());
        assertTrue(Amount.of(0).isZero());
        assertTrue(Amount.of(0, 0).isZero());
        assertTrue(Amount.of("-0.0").isZero());

        assertFalse(Amount.of("0.00000000000000000000000000000000000001").isZero());
        assertFalse(Amount.of("-0.00000000000000000000000000000000000001").isZero());
    }

    @SuppressWarnings("MagicNumber")
    @Test
    public void add() {
        assertThat(Amount.of(10, 10).add(Amount.of(4, 20)), is(Amount.of(14,30)));
        assertThat(Amount.of(10, 10).add(Amount.of(-4, 20)), is(Amount.of(5,90)));
    }

    @SuppressWarnings("MagicNumber")
    @Test
    public void subtract() {
        assertThat(Amount.of(10, 10).subtract(Amount.of(4, 20)), is(Amount.of(5,90)));
        assertThat(Amount.of(10, 10).subtract(Amount.of(-4, 20)), is(Amount.of(14,30)));
    }

    @SuppressWarnings("MagicNumber")
    @Test
    public void asString() {
        assertThat(Amount.of(10, 10).asString(), is("10.10"));
        assertThat(Amount.of(-1, 30).asString(), is("-1.30"));
    }

}