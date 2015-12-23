package se.eris.util;

import org.jetbrains.annotations.NotNull;

public class StringLengthLimit implements StringLimit {

    private final int min;
    private final int max;

    @NotNull
    public static StringLengthLimit max(final int max) {
        return of(0, max);
    }

    @NotNull
    public static StringLengthLimit of(final int min, final int max) {
        return new StringLengthLimit(min, max);
    }

    private StringLengthLimit(final int min, final int max) {
        if (min < 0) {
            throw new IllegalArgumentException("Min " + min + " less than zero");
        }
        if (min > max) {
            throw new IllegalArgumentException("Min " + min + " greater than max '" + max + "'");
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(@NotNull final String s) {
        if (s.length() < min) {
            throw new IllegalArgumentException("Length of " + s + " is less than min " + min);
        }
        if (s.length() > max) {
            throw new IllegalArgumentException("Length of " + s + " is greater than max " + max);
        }
    }

}
