package se.eris.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class IOStrings {

    @NotNull
    public static Optional<Double> stringToDouble(@Nullable final String s) {
        try {
            return Optional.of(Double.valueOf(Double.parseDouble(s)));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }

    @NotNull
    public static Optional<Integer> stringToInteger(@Nullable final String s) {
        try {
            return Optional.of(Integer.valueOf(Integer.parseInt(s)));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }

}
