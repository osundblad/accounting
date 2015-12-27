package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.limit.LimitedBigDecimal;
import se.eris.type.BasicWrapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Amount extends BasicWrapper<BigDecimal> {

    public static final int DECIMALS = 2;

    private static final LimitedBigDecimal LIMIT = LimitedBigDecimal.init().decimals(DECIMALS).build();

    @NotNull
    public static final Amount ZERO = new Amount(BigDecimal.ZERO);

    @NotNull
    private static Amount of(@NotNull final BigDecimal bigDecimal) {
        return new Amount(bigDecimal);
    }

    @NotNull
    public static Amount of(final int amount) {
        return new Amount(new BigDecimal(amount));
    }

    @NotNull
    public static Amount of(@NotNull final String amount) {
        return new Amount(new BigDecimal(amount));
    }

    private Amount(@NotNull final BigDecimal amount) {
        super(LIMIT.of(amount).setScale(DECIMALS, BigDecimal.ROUND_UNNECESSARY));
    }

    @NotNull
    public Amount add(@NotNull final Amount amount) {
        return new Amount(this.raw().add(amount.raw()));
    }

    @NotNull
    public Amount subtract(@NotNull final Amount amount) {
        return new Amount(this.raw().subtract(amount.raw()));
    }

    @NotNull
    public AmountPair split(@NotNull final BigDecimal percent) {
        if ((percent.signum() != -1) && (percent.subtract(BigDecimal.valueOf(100)).signum() != 1)) {
            final Amount first = percent(percent);
            final Amount second = subtract(first);
            return AmountPair.of(first, second);
        } else {
            throw new AssertionError();
        }
    }

    @NotNull
    public Amount percent(@NotNull final BigDecimal percent) {
        return Amount.of(raw().multiply(percent).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP).setScale(DECIMALS, RoundingMode.HALF_UP));
    }

    public boolean isZero() {
        return raw().compareTo(BigDecimal.ZERO) == 0;
    }

    @NotNull
    public String asString() {
        return raw().toPlainString();
    }

}
