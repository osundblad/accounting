package se.eris.accounting.model.book.transaction;

import se.eris.jtype.limit.LimitedBigDecimal;
import se.eris.jtype.type.BasicWrapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Amount extends BasicWrapper<BigDecimal> {

    public static final int DECIMALS = 2;
    private static final BigDecimal BIG_DECIAML_100 = BigDecimal.valueOf(100);

    private static final LimitedBigDecimal LIMIT = LimitedBigDecimal.init().decimals(DECIMALS).build();

    public static final Amount ZERO = new Amount(BigDecimal.ZERO);

    private static Amount of(final BigDecimal bigDecimal) {
        return new Amount(bigDecimal);
    }

    public static Amount of(final int amount) {
        return new Amount(new BigDecimal(amount));
    }

    public static Amount of(final String amount) {
        return new Amount(new BigDecimal(amount));
    }

    private Amount(final BigDecimal amount) {
        super(LIMIT.of(amount).setScale(DECIMALS, BigDecimal.ROUND_UNNECESSARY));
    }

    private Amount internalAdd(final Amount amount) {
        return new Amount(this.raw().add(amount.raw()));
    }

    public Amount add(final Amount... amounts) {
        Amount sum = this;
        for (final Amount amount : amounts) {
            sum = sum.internalAdd(amount);
        }
        return sum;
    }

    public Amount subtract(final Amount amount) {
        return new Amount(this.raw().subtract(amount.raw()));
    }

    public Amount multiply(final Amount amount) {
        return new Amount(this.raw().multiply(amount.raw()).setScale(DECIMALS, BigDecimal.ROUND_HALF_UP));
    }

    public Amount divide(final Amount amount) {
        //noinspection BigDecimalMethodWithoutRoundingCalled
        return new Amount(this.raw().divide(amount.raw()).setScale(DECIMALS, BigDecimal.ROUND_HALF_UP));
    }

    public Amount percent(final Amount percent) {
        //noinspection BigDecimalMethodWithoutRoundingCalled
        return Amount.of(raw().multiply(percent.raw()).divide(BIG_DECIAML_100).setScale(DECIMALS, RoundingMode.HALF_UP));
    }

    public boolean isZero() {
        return raw().compareTo(BigDecimal.ZERO) == 0;
    }

    public String asString() {
        return raw().toPlainString();
    }
}
