package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class Transaction {

    @Nullable
    private final UUID id;

    @NotNull
    private final UUID bookYearId;
    @NotNull
    private final LocalDate date;
    @NotNull
    private final Collection<TransactionLine> collection;

    public Transaction(@Nullable final UUID id, @NotNull final UUID bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> collection) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.date = date;
        this.collection = collection;
        validate();
    }

    private void validate() {
        if (!sumOfLineAmounts().isZero()) {
            throw new NonZeroSumTransactionException(this);
        }
    }

    @NotNull
    private Amount sumOfLineAmounts() {
        Amount sum = Amount.ZERO;
        for (final TransactionLine transactionLine : collection) {
            sum = sum.add(transactionLine.getAmount());
        }
        return sum;
    }

    public boolean hasId() {
        return id != null;
    }

    @NotNull
    public UUID getId() {
        if (id == null) {
            throw new NotPersistedException(this);
        }
        return id;
    }

    @Nullable
    public UUID getIdRaw() {
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
