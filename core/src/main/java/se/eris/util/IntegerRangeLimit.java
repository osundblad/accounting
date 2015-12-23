package se.eris.util;

import org.jetbrains.annotations.NotNull;

public class IntegerRangeLimit implements Limit<Integer> {

    private final int min;
    private final int max;

    @NotNull
    public static IntegerRangeLimit max(final int max) {
        return of(0, max);
    }

    @NotNull
    public static IntegerRangeLimit of(final int min, final int max) {
        return new IntegerRangeLimit(min, max);
    }

    private IntegerRangeLimit(final int min, final int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min " + min + " greater than max '" + max + "'");
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(@NotNull final Integer i) {
        if (i < min) {
            throw new IllegalArgumentException(i + " is less than min " + min);
        }
        if (i > max) {
            throw new IllegalArgumentException(i + " is greater than max " + max);
        }
    }

}
