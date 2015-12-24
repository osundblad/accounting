package se.eris.util;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class StringByteLengthLimit implements StringLimit {

    private final int min;
    private final int max;
    private final Charset charset;

    @NotNull
    public static StringByteLengthLimit max(final int max) {
        return of(0, max);
    }

    @NotNull
    public static StringByteLengthLimit of(final int min, final int max) {
        return of(min, max, Optional.empty());
    }

    @NotNull
    public static StringByteLengthLimit of(final int min, final int max, @NotNull final Optional<Charset> charset) {
        return new StringByteLengthLimit(min, max, charset);
    }

    private StringByteLengthLimit(final int min, final int max, @NotNull final Optional<Charset> charset) {
        this.charset = charset.orElse(StandardCharsets.UTF_8);
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
        final int length = getLength(s);
        if (length < min) {
            throw new IllegalArgumentException("Byte length of " + s + " is less than min " + min);
        }
        if (length > max) {
            throw new IllegalArgumentException("Byte length of " + s + " is greater than max " + max);
        }
    }

    private int getLength(@NotNull final String s) {
        return s.getBytes(charset).length;
    }

}
