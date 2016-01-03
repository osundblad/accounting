package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class StringRegexpLimit implements StringLimit {

    @NotNull
    public static StringRegexpLimit of(@NotNull final Pattern pattern) {
        return new StringRegexpLimit(pattern);
    }

    @NotNull
    public static StringRegexpLimit of(@NotNull final String regexp) {
        return of(Pattern.compile(regexp));
    }

    @NotNull
    private final Pattern pattern;

    private StringRegexpLimit(@NotNull final Pattern pattern) {
        this.pattern = pattern;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final String s) {
        if (!pattern.matcher(s).matches()) {
            return ValidationMessages.of("'" + s + "' does not match " + pattern);
        }
        return ValidationMessages.empty();
    }

}
