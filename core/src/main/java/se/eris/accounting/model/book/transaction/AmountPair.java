package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.type.SimplePair;

public class AmountPair extends SimplePair<Amount> {

    public static AmountPair of(@NotNull final Amount first, @NotNull final Amount second) {
        return new AmountPair(first, second);
    }

    private AmountPair(@NotNull final Amount first, @NotNull final Amount second) {
        super(first, second);
    }

    @NotNull
    public Amount getFirst() {
        return rawFirst();
    }

    @NotNull
    public Amount getSecond() {
        return rawSecond();
    }

}