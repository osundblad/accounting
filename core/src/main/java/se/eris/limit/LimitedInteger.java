package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LimitedInteger extends AbstractLimited<Integer> {

    @NotNull
    public static LimitedInteger.Builder init() {
        return new Builder();
    }

    @NotNull
    @Override
    public Integer of(@NotNull final Integer i) {
        return super.of(i);
    }

    private LimitedInteger(@NotNull final List<? extends Limit<Integer>> limits) {
        super(limits);
    }

    public static class Builder extends AbstractLimited.Builder<Integer> {

        @NotNull
        public Builder range(final int max) {
            limit(IntegerRangeLimit.zeroTo(max));
            return this;
        }

        @NotNull
        public Builder range(final int min, final int max) {
            limit(IntegerRangeLimit.of(min, max));
            return this;
        }

        @NotNull
        public LimitedInteger build() {
            return new LimitedInteger(limits);
        }
    }

}
