package se.eris.accounting.model.book.transaction;

import se.eris.jtype.type.PairWrapper;

public class AmountPair extends PairWrapper<Amount> {

    public static AmountPair of(final Amount first, final Amount second) {
        return new AmountPair(first, second);
    }

    private AmountPair(final Amount first, final Amount second) {
        super(first, second);
    }

    public Amount getFirst() {
        return rawFirst();
    }

    public Amount getSecond() {
        return rawSecond();
    }

}
