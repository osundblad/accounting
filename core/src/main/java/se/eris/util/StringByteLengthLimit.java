package se.eris.util;

import org.jetbrains.annotations.NotNull;

public class StringByteLengthLimit implements StringLimit {

    private final int min;
    private final int max;

    @NotNull
    public static StringByteLengthLimit max(final int max) {
        return of(0, max);
    }

    @NotNull
    public static StringByteLengthLimit of(final int min, final int max) {
        return new StringByteLengthLimit(min, max);
    }

    private StringByteLengthLimit(final int min, final int max) {
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
        final int length = s.getBytes().length;
        if (length < min) {
            throw new IllegalArgumentException("Byte length of " + s + " is less than min " + min);
        }
        if (length > max) {
            throw new IllegalArgumentException("Byte length of " + s + " is greater than max " + max);
        }
    }

}
