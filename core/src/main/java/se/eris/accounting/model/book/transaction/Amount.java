package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.type.BasicWrapper;

import java.math.BigDecimal;

public final class Amount extends BasicWrapper<BigDecimal> {

    @NotNull
    private static final BigDecimal DIVISOR = new BigDecimal(100);
    @NotNull
    public static final Amount ZERO = Amount.of(0, 0);

    @NotNull
    private static Amount part(final int decimalPart) {
        validateDecimalPart(decimalPart);
        return new Amount(new BigDecimal(decimalPart).divide(DIVISOR, 2, BigDecimal.ROUND_UNNECESSARY));
    }

    private static void validateDecimalPart(final int decimalPart) {
        if ((decimalPart < 0) || (decimalPart >= 100)) {
            throw new IllegalArgumentException("Invalid decimal part " + decimalPart);
        }
    }

    @NotNull
    public static Amount of(final int amount, final int decimalPart) {
        final Amount part = part(decimalPart);
        return (amount >= 0) ? of(amount).add(part) : of(amount).subtract(part);
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
        super(amount);
    }

    @NotNull
    public Amount add(@NotNull final Amount amount) {
        return new Amount(this.raw().add(amount.raw()));
    }

    @NotNull
    public Amount subtract(@NotNull final Amount amount) {
        return new Amount(this.raw().subtract(amount.raw()));
    }

    public boolean isZero() {
        return raw().compareTo(BigDecimal.ZERO) == 0;
    }

    @NotNull
    public String asString() {
        return raw().toString();
    }

}
