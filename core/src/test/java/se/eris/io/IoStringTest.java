package se.eris.io;

import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SuppressWarnings("MagicNumber")
public class IoStringTest {

    @Test
    public void toDouble() {
        assertThat(IoString.toDouble("1.7").get(), is(1.7));
        assertThat(IoString.toDouble("0").get(), is(0d));
        assertThat(IoString.toDouble("-0").get(), is(-0d));
        assertThat(IoString.toDouble("-17.4711").get(), is(-17.4711));
        assertTrue(IoString.toDouble("1.5E-2").isPresent());

        assertTrue(IoString.toDouble("NaN").isPresent());
        assertTrue(IoString.toDouble("Infinity").isPresent());

        assertFalse(IoString.toDouble("").isPresent());
        assertFalse(IoString.toDouble("a").isPresent());
        assertFalse(IoString.toDouble(null).isPresent());
    }

    @Test
    public void toInteger() {
        assertThat(IoString.toInteger("1234567").get(), is(1234567));
        assertThat(IoString.toInteger("0").get(), is(0));
        assertThat(IoString.toInteger("-0").get(), is(0));
        assertThat(IoString.toInteger("-17").get(), is(-17));
        assertThat(IoString.toInteger("016").get(), is(16));

        assertFalse(IoString.toInteger("").isPresent());
        assertFalse(IoString.toInteger("0xF").isPresent());
        assertFalse(IoString.toInteger("a").isPresent());
        assertFalse(IoString.toInteger(null).isPresent());
    }

    @Test
    public void toShort() {
        assertThat(IoString.toShort("0").get(), is((short) 0));
        assertThat(IoString.toShort("-0").get(), is((short) 0));
        assertThat(IoString.toShort("-17").get(), is((short) -17));
        assertThat(IoString.toShort("016").get(), is((short) 16));

        assertFalse(IoString.toShort("1234567").isPresent());
        assertFalse(IoString.toShort("").isPresent());
        assertFalse(IoString.toShort("0xF").isPresent());
        assertFalse(IoString.toShort("a").isPresent());
        assertFalse(IoString.toShort(null).isPresent());
    }

    @Test
    public void toBoolean() {
        assertThat(IoString.toBoolean("true").get(), is(true));
        assertThat(IoString.toBoolean("TruE").get(), is(true));

        assertThat(IoString.toBoolean("false").get(), is(false));
        assertThat(IoString.toBoolean(null).get(), is(false));
    }

    @Test
    public void toUUID() {
        final UUID uuid = UUID.randomUUID();
        assertThat(IoString.toUUID(uuid.toString()).get(), is(uuid));

        assertFalse(IoString.toUUID("abc").isPresent());
    }

    @Test
    public void toLocalDate() {
        final LocalDate now = LocalDate.now();
        assertThat(IoString.toLocalDate(now.toString()).get(), is(now));

        assertFalse(IoString.toLocalDate("abc").isPresent());
    }

}