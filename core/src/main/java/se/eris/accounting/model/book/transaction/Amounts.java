package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;

public final class Amounts {

    public static Amount sum(@NotNull final Amount... amounts) {
        Amount sum = Amount.ZERO;
        for (final Amount amount : amounts) {
            sum = sum.add(amount);
        }
        return sum;
    }

    public static AmountPair split(@NotNull final Amount amount, @NotNull final Amount percent) {
        final Amount first = amount.percent(percent);
        final Amount second = amount.subtract(first);
        return AmountPair.of(first, second);
    }

}
