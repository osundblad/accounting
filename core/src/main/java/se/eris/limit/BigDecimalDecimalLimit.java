package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BigDecimalDecimalLimit implements Limit<BigDecimal> {

    private final int decimals;

    public BigDecimalDecimalLimit(final int decimals) {
        this.decimals = decimals;
    }

    @Override
    public void validate(@NotNull final BigDecimal item) {
        if (item.scale() > decimals) {
            throw new IllegalArgumentException("Too many decimals " + item.toString() + " (only " + decimals + " allowed)");
        }
    }

    @NotNull
    public static Limit<BigDecimal> of(final int decimals) {
        return new BigDecimalDecimalLimit(decimals);
    }

}
