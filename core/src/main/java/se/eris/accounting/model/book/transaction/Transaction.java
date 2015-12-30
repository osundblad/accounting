package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.BookYearId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class Transaction {

    @NotNull
    public static Transaction of(@NotNull final Optional<TransactionId> id, @NotNull final BookYearId bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> transactionLines) {
        return new Transaction(id, bookYearId, date, transactionLines);
    }

    @NotNull
    private final Optional<TransactionId> id;
    @NotNull
    private final BookYearId bookYearId;
    @NotNull
    private final LocalDate date;
    @NotNull
    private final Collection<TransactionLine> transactionLines;

    private Transaction(@NotNull final Optional<TransactionId> id, @NotNull final BookYearId bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> transactionLines) {
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
    public Optional<TransactionId> getId() {
        return id;
    }

    @NotNull
    public BookYearId getBookYearId() {
        return bookYearId;
    }

    @NotNull
    public LocalDate getDate() {
        return date;
    }

    @NotNull
    public Stream<TransactionLine> getTransactionLines() {
        return transactionLines.stream();
    }

}
