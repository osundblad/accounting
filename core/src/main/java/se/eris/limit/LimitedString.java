package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

public class LimitedString extends AbstractLimited<String> {

    @NotNull
    public static LimitedString.Builder init() {
        return new Builder();
    }

    @NotNull
    @Override
    public String of(@NotNull final String s) {
        return super.of(s);
    }

    private LimitedString(@NotNull final List<? extends Limit<String>> limits) {
        super(limits);
    }

    public static class Builder extends AbstractLimited.Builder<String> {

        @NotNull
        public Builder length(final int min, final int max) {
            limit(StringLengthLimit.of(min, max));
            return this;
        }

        @NotNull
        public Builder length(final int max) {
            limit(StringLengthLimit.zeroTo(max));
            return this;
        }

        @NotNull
        public Builder matches(@NotNull final Pattern pattern) {
            limit(StringRegexpLimit.of(pattern));
            return this;
        }

        @NotNull
        public Builder matches(@NotNull final String regexp) {
            limit(StringRegexpLimit.of(regexp));
            return this;
        }

        @NotNull
        public LimitedString build() {
            return new LimitedString(limits);
        }

    }

}
