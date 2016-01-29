package se.eris.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.UUID;

/**
 * Todo:
 *  - How to handle "" (empty String)
 */
@SuppressWarnings("WeakerAccess")
public final class IoString {

    private IoString() {
    }

    @NotNull
    public static Optional<Double> toDouble(@Nullable final String s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Double.parseDouble(s));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(s + " is not a Double", e);
        }
    }

    @NotNull
    public static Optional<Integer> toInteger(@Nullable final String s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(s + " is not an Integer", e);
        }
    }

    @NotNull
    public static Optional<Short> toShort(@Nullable final String s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Short.parseShort(s));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(s + " is not a Short", e);
        }
    }

    /**
     * Maybe return boolean or be more strict (allow only true/false)
     */
    @NotNull
    public static Optional<Boolean> toBoolean(@Nullable final String s) {
        return Optional.of(Boolean.parseBoolean(s));
    }

    @NotNull
    public static Optional<UUID> toUUID(@Nullable final String s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(UUID.fromString(s));
        } catch (final IllegalArgumentException e) {
            throw new IllegalArgumentException(s + " is not a UUID", e);
        }
    }

    @NotNull
    public static Optional<LocalDate> toLocalDate(@Nullable final CharSequence s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(LocalDate.parse(s));
        } catch (final DateTimeParseException e) {
            throw new IllegalArgumentException(s + " is not a LocalDate", e);
        }
    }

}
