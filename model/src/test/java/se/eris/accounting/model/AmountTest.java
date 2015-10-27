package se.eris.accounting.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AmountTest {

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
    public void testHashCode() {
        assertThat(Amount.of(10, 1).hashCode(), is(Amount.of(10, 0).hashCode()));
        assertThat(Amount.of(-10, 1).hashCode(), is(Amount.of(-10, 0).hashCode()));
        assertThat(Amount.of(-5).hashCode(), is(Amount.of("-5.4").hashCode()));
        assertThat(Amount.of(10, 0).hashCode(), is(Amount.of(new BigDecimal(10.333333333)).hashCode()));
    }

}