package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BigDecimalRangeLimit implements Limit<BigDecimal> {

    @NotNull
    public static BigDecimalRangeLimit zeroTo(final BigDecimal max) {
        return of(BigDecimal.ZERO, max);
    }

    @NotNull
    public static BigDecimalRangeLimit of(final BigDecimal min, final BigDecimal max) {
        return new BigDecimalRangeLimit(min, max);
    }

    private final BigDecimal min;
    private final BigDecimal max;

    private BigDecimalRangeLimit(final BigDecimal min, final BigDecimal max) {
        if (min.compareTo(max) == 1) {
            throw new IllegalArgumentException("Min " + min + " greater than max '" + max + "'");
        }
        this.min = min;
        this.max = max;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final BigDecimal bigDecimal) {
        if (bigDecimal.compareTo(min) == -1) {
            return ValidationMessages.of(bigDecimal + " is less than min " + min);
        }
        if (bigDecimal.compareTo(max) == 1) {
            return ValidationMessages.of(bigDecimal + " is greater than max " + max);
        }
        return ValidationMessages.empty();
    }

}
