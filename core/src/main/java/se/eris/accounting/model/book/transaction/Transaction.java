package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;

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

    public Transaction(@NotNull final Optional<UUID> id, @NotNull final UUID bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> transactionLines) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.date = date;
        this.transactionLines = new ArrayList<>(transactionLines);
        validate();
    }

    private void validate() {
        validateSumOfLinesIsZero();
    }

    private void validateSumOfLinesIsZero() {
        if (!sumOfTransactionLines().isZero()) {
            throw new NonZeroSumTransactionException(this);
        }
    }

    @NotNull
    private Amount sumOfTransactionLines() {
        return transactionLines.stream().map(TransactionLine::getAmount).reduce(Amount.ZERO, Amount::add);
    }

    @NotNull
    public Optional<UUID> getId() {
        return id;
    }

    @NotNull
    public UUID getBookYearId() {
        return bookYearId;
    }

    @NotNull
    public LocalDate getDate() {
        return date;
    }

}