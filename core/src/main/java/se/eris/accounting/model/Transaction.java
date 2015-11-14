package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Transaction {

    @NotNull
    private final Optional<UUID> id;
    @NotNull
    private final UUID bookYearId;
    @NotNull
    private final LocalDate date;
    @NotNull
    private final Collection<TransactionLine> transactionLines;

    public Transaction(@Nullable final UUID id, @NotNull final UUID bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> transactionLines) {
        this.id = Optional.ofNullable(id);
        this.bookYearId = bookYearId;
        this.date = date;
        this.transactionLines = new ArrayList<>(transactionLines);
        validate();
    }

    private void validate() {
        validateSumIsZero();
    }

    private void validateSumIsZero() {
        if (!sumOfTransactionLines().isZero()) {
            throw new NonZeroSumTransactionException(this);
        }
    }

    @NotNull
    private Amount sumOfTransactionLines() {
        return transactionLines.stream().map(TransactionLine::getAmount).reduce(Amount.ZERO, Amount::add);
    }

    @NotNull
    public UUID getId() {
        return id.orElseThrow(() -> new NotPersistedException(this));
    }

    @NotNull
    public Optional<UUID> getIdRaw() {
        return id;
    }

    @NotNull
    public LocalDate getDate() {
        return date;
    }

    @NotNull
    public UUID getBookYearId() {
        return bookYearId;
    }

}
