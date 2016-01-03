package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public class IntegerRangeLimit implements Limit<Integer> {

    @NotNull
    public static IntegerRangeLimit zeroTo(final int max) {
        return of(0, max);
    }

    @NotNull
    public static IntegerRangeLimit of(final int min, final int max) {
        return new IntegerRangeLimit(min, max);
    }

    private final int min;
    private final int max;

    private IntegerRangeLimit(final int min, final int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min " + min + " greater than max '" + max + "'");
        }
        this.min = min;
        this.max = max;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final Integer i) {
        if (i < min) {
            return ValidationMessages.of(i + " is less than min " + min);
        }
        if (i > max) {
            return ValidationMessages.of(i + " is greater than max " + max);
        }
        return ValidationMessages.empty();
    }

}
