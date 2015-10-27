package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public final class Amount {

    @NotNull
    private static final BigDecimal DIVISOR = new BigDecimal(100);
    @NotNull
    public static final Amount ZERO = Amount.of(0, 0);

    @NotNull
    public static Amount of(final int amount, final int decimalPart) {
        assert (decimalPart >= 0) && (decimalPart < 100);
        final BigDecimal decimal = new BigDecimal((amount >= 0) ? decimalPart : -decimalPart).divide(DIVISOR, 2, BigDecimal.ROUND_UNNECESSARY);
        return of(new BigDecimal(amount).add(decimal));
    }

    @NotNull
    public static Amount of(final int amount) {
        return of(amount, 0);
    }

    @NotNull
    public static Amount of(@NotNull final BigDecimal amount) {
        return new Amount(amount);
    }

    @NotNull
    public static Amount of(@NotNull final String amount) {
        return of(new BigDecimal(amount));
    }

    @NotNull
    private final BigDecimal amount;

    private Amount(@NotNull final BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    public Amount add(@NotNull final Amount amount) {
        return of(this.amount.add(amount.amount));
    }

    public boolean isZero() {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }

    @NotNull
    public BigDecimal asBigDecimal() {
        return amount;
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final Amount that = (Amount) o;

        return this.amount.compareTo(that.amount) == 0;
    }

    @Override
    public int hashCode() {
        return amount.intValue();
    }

}
