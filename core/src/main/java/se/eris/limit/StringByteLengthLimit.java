package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class StringByteLengthLimit implements StringLimit {

    @NotNull
    public static StringByteLengthLimit zeroTo(final int max) {
        return of(0, max);
    }

    @NotNull
    public static StringByteLengthLimit of(final int min, final int max) {
        return new StringByteLengthLimit(min, max, Optional.empty());
    }

    @NotNull
    public static StringByteLengthLimit of(final int min, final int max, @NotNull final Charset charset) {
        return new StringByteLengthLimit(min, max, Optional.of(charset));
    }

    private final int min;
    private final int max;
    private final Charset charset;

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

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final String s) {
        final int length = getByteLength(s);
        if (length < min) {
            return ValidationMessages.of("Byte length of " + s + " is less than min " + min);
        }
        if (length > max) {
            return ValidationMessages.of("Byte length of " + s + " is greater than max " + max);
        }
        return ValidationMessages.empty();
    }

    private int getByteLength(@NotNull final String s) {
        return s.getBytes(charset).length;
    }

}
