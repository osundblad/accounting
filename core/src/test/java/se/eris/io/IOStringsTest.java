package se.eris.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IOStringsTest {

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "MagicNumber"})
    @Test
    public void stringToDouble() {
        assertThat(IOStrings.stringToDouble("1.7").get(), is(1.7d));
        assertThat(IOStrings.stringToDouble("0").get(), is(0d));
        assertThat(IOStrings.stringToDouble("-0").get(), is(-0d));
        assertThat(IOStrings.stringToDouble("-17.4711").get(), is(-17.4711));
        assertTrue(IOStrings.stringToDouble("1.5E-2").isPresent());

        assertTrue(IOStrings.stringToDouble("NaN").isPresent());
        assertTrue(IOStrings.stringToDouble("Infinity").isPresent());

        assertFalse(IOStrings.stringToDouble("").isPresent());
        assertFalse(IOStrings.stringToDouble("a").isPresent());
        assertFalse(IOStrings.stringToDouble(null).isPresent());
    }

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "MagicNumber"})
    @Test
    public void stringToInteger() {
        assertThat(IOStrings.stringToInteger("1234567").get(), is(1234567));
        assertThat(IOStrings.stringToInteger("0").get(), is(0));
        assertThat(IOStrings.stringToInteger("-0").get(), is(0));
        assertThat(IOStrings.stringToInteger("-17").get(), is(-17));
        assertThat(IOStrings.stringToInteger("016").get(), is(16));

        assertFalse(IOStrings.stringToInteger("").isPresent());
        assertFalse(IOStrings.stringToInteger("0xF").isPresent());
        assertFalse(IOStrings.stringToInteger("a").isPresent());
        assertFalse(IOStrings.stringToInteger(null).isPresent());
    }
}