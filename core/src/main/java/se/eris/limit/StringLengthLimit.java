package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public class StringLengthLimit implements StringLimit {

    public static final int LONGEST_STRING_TO_PRESENT = 80;
    private final int min;
    private final int max;

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

    @NotNull
    public static StringLengthLimit zeroTo(final int max) {
        return of(0, max);
    }

    @NotNull
    public static StringLengthLimit of(final int min, final int max) {
        return new StringLengthLimit(min, max);
    }

    @Override
    public void validate(@NotNull final String s) {
        final int length = s.length();
        if (length < min) {
            throw new IllegalArgumentException("Length violation: " + quote(shorten(s)) + " is shorter (" + length + ") than the minimum length of " + min + ".");
        }
        if (length > max) {
            throw new IllegalArgumentException("Length violation: " + quote(shorten(s)) + " is longer (" + length + ") than the maximum length of " + max + ".");
        }
    }

    @NotNull
    private String quote(@NotNull final String s) {
        return '\'' + s + '\'';
    }

    @NotNull
    private String shorten(@NotNull final String s) {
        return (s.length() > LONGEST_STRING_TO_PRESENT) ? truncate(s) : s;
    }

    @NotNull
    private String truncate(@NotNull final String s) {
        return s.substring(0, LONGEST_STRING_TO_PRESENT) + "...' (truncated at " + LONGEST_STRING_TO_PRESENT + " characters)";
    }

}
