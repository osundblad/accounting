package se.eris.util;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class StringRegexpLimit implements StringLimit {

    @NotNull
    private final Pattern pattern;

    @NotNull
    public static StringRegexpLimit of(@NotNull final Pattern pattern) {
        return new StringRegexpLimit(pattern);
    }

    @NotNull
    public static StringRegexpLimit of(@NotNull final String regexp) {
        return of(Pattern.compile(regexp));
    }

    private StringRegexpLimit(@NotNull final Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void validate(@NotNull final String s) {
        if (!pattern.matcher(s).matches()) {
            throw new IllegalArgumentException("'" + s + "' does not match " + pattern);
        }
    }

}