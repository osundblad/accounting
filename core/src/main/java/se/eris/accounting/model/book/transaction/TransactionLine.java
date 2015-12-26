package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.account.BookYearAccountId;

import java.util.Optional;

public class TransactionLine {

    @NotNull
    private final Optional<TransactionLineId> id;
    @NotNull
    private final BookYearAccountId bookYearAccountId;
    @NotNull
    private final Amount amount;

    public TransactionLine(@Nullable final TransactionLineId id, @NotNull final BookYearAccountId bookYearAccountId, @NotNull final Amount amount) {
        this.id = Optional.ofNullable(id);
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
