package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AmountsTest {

    @NotNull
    private static final Amount PERCENT_0£25 = Amount.of("0.25");

    @Test
    public void split() {
        final AmountPair split1000 = Amounts.split(Amount.of("1000"), Amount.of(25));

        assertThat(split1000.getFirst(), is(Amount.of("250")));
        assertThat(split1000.getSecond(), is(Amount.of("750")));
    }

    @Test
    public void split_lessThanZeroPercent() {
        final AmountPair split1000 = Amounts.split(Amount.of("1000"), Amount.of("-25"));
        assertThat(split1000.getFirst(), is(Amount.of("-250")));
        assertThat(split1000.getSecond(), is(Amount.of("1250")));
    }

    @Test
    public void split_sumIsOriginalAmount() {
        final AmountPair split10 = Amounts.split(Amount.of("10"), PERCENT_0£25);

        assertThat(split10.getFirst(), is(Amount.of("0.03")));
        assertThat(split10.getSecond(), is(Amount.of("9.97")));
    }

}