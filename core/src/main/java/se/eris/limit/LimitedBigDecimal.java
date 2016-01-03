package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class LimitedBigDecimal extends AbstractLimited<BigDecimal> {

    @NotNull
    public static LimitedBigDecimal.Builder init() {
        return new Builder();
    }

    @NotNull
    @Override
    public BigDecimal of(@NotNull final BigDecimal i) {
        return super.of(i);
    }

    private LimitedBigDecimal(@NotNull final List<Function<BigDecimal, ValidationMessages>> limits, @NotNull final ValidationBehavior validationBehavior) {
        super(limits, validationBehavior);
    }

    public static class Builder extends AbstractLimited.Builder<BigDecimal> {

        @NotNull
        public Builder decimals(final int decimals) {
            limit(BigDecimalDecimalLimit.of(decimals));
            return this;
        }

        @NotNull
        public LimitedBigDecimal build() {
            return new LimitedBigDecimal(limits, validationBehavior);
        }
    }

}
