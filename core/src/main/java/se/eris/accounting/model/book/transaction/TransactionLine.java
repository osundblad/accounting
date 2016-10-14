package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.account.BookYearAccountId;

import java.util.Optional;

public final class TransactionLine {

    @NotNull
    public static TransactionLine of(@NotNull final Optional<TransactionLineId> id, @NotNull final BookYearAccountId bookYearAccountId, @NotNull final Amount amount) {
        return new TransactionLine(id, bookYearAccountId, amount);
    }

    @NotNull
    private final Optional<TransactionLineId> id;

    @NotNull
    private final BookYearAccountId bookYearAccountId;

    @NotNull
    private final Amount amount;

    private TransactionLine(@NotNull final Optional<TransactionLineId> id, @NotNull final BookYearAccountId bookYearAccountId, @NotNull final Amount amount) {
        this.id = id;
        this.bookYearAccountId = bookYearAccountId;
        this.amount = amount;
    }

    @NotNull
    public Optional<TransactionLineId> getId() {
        return id;
    }

    @NotNull
    public BookYearAccountId getBookYearAccountId() {
        return bookYearAccountId;
    }

    @NotNull
    public Amount getAmount() {
        return amount;
    }

}
