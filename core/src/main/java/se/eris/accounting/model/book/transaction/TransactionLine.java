package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class TransactionLine {

    @NotNull
    private final Optional<UUID> id;
    @NotNull
    private final UUID bookYearAccountId;
    @NotNull
    private final Amount amount;

    public TransactionLine(@Nullable final UUID id, @NotNull final UUID bookYearAccountId, @NotNull final BigDecimal amount) {
        this.id = Optional.ofNullable(id);
        this.bookYearAccountId = bookYearAccountId;
        this.amount = Amount.of(amount);
    }

    @NotNull
    public Optional<UUID> getId() {
        return id;
    }

    @NotNull
    public UUID getBookYearAccountId() {
        return bookYearAccountId;
    }

    @NotNull
    public Amount getAmount() {
        return amount;
    }

}
