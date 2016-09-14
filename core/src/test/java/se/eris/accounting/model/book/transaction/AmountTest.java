package se.eris.accounting.model.book.transaction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AmountTest {

    private static final Amount PERCENT_0£25 = Amount.of("0.25");

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void of_valid() {
        Amount.of("0.03");
        Amount.of("-0.03");
        Amount.of(3);
    }

    @Test
    public void of_moreThan2Decimals_shouldFail() {
        exception.expect(IllegalArgumentException.class);
        Amount.of("0.003");
    }

    @Test
    public void isZero() {
        assertTrue(Amount.ZERO.isZero());
        assertTrue(Amount.of(0).isZero());
        assertTrue(Amount.of("0").isZero());
        assertTrue(Amount.of("-0.0").isZero());
    }

    @Test
    public void add() {
        assertThat(Amount.of("10.10").add(Amount.of("4.20")), is(Amount.of("14.30")));
        assertThat(Amount.of("10.10").add(Amount.of("-4.20")), is(Amount.of("5.90")));
    }

    @Test
    public void subtract() {
        assertThat(Amount.of("10.10").subtract(Amount.of("4.20")), is(Amount.of("5.90")));
        assertThat(Amount.of("10.10").subtract(Amount.of("-4.20")), is(Amount.of("14.30")));
    }

    @Test
    public void percent() {
        assertThat(Amount.of("1000").percent(Amount.of(5)), is(Amount.of("50")));
    }

    @Test
    public void percent_roundHalfUp() {
        assertThat(Amount.of("10").percent(PERCENT_0£25), is(Amount.of("0.03")));
    }

    @Test
    public void amount_decimals_is2() {
        assertThat(Amount.of("1").raw().scale(), is(Amount.DECIMALS));
        assertThat(Amount.of("1").percent(PERCENT_0£25).raw().scale(), is(Amount.DECIMALS));
    }

    @Test
    public void asString() {
        assertThat(Amount.of("10.10").asString(), is("10.10"));
        assertThat(Amount.of("-1.30").asString(), is("-1.30"));
        assertThat(Amount.of("1.3").asString(), is("1.30"));
    }

}