package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BigDecimalDecimalLimit implements Limit<BigDecimal> {

    @NotNull
    public static Limit<BigDecimal> of(final int decimals) {
        return new BigDecimalDecimalLimit(decimals);
    }

    private final int decimals;

    private BigDecimalDecimalLimit(final int decimals) {
        this.decimals = decimals;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final BigDecimal item) {
        if (item.scale() > decimals) {
            return ValidationMessages.of("Too many decimals " + item.toString() + " (only " + decimals + " allowed)");
        }
        return ValidationMessages.empty();
    }

}
