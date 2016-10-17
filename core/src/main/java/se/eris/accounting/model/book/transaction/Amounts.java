package se.eris.accounting.model.book.transaction;

public final class Amounts {

    public static Amount sum(final Amount... amounts) {
        Amount sum = Amount.ZERO;
        for (final Amount amount : amounts) {
            sum = sum.add(amount);
        }
        return sum;
    }

    public static AmountPair split(final Amount amount, final Amount percent) {
        final Amount first = amount.percent(percent);
        final Amount second = amount.subtract(first);
        return AmountPair.of(first, second);
    }

}
