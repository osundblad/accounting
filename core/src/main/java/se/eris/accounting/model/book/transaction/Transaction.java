package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.BookYearId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class Transaction {

        public static Transaction of(@NotNull final Optional<TransactionId> id, @NotNull final BookYearId bookYearId, @NotNull final LocalDate date, @NotNull final Collection<TransactionLine> transactionLines) {
        return new Transaction(id, bookYearId, date, transactionLines);
    }

        private final Optional<TransactionId> id;
        private final BookYearId bookYearId;
        private final LocalDate date;
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

        private Amount sumOfTransactionLines() {
        return transactionLines.stream().map(TransactionLine::getAmount).reduce(Amount.ZERO, Amount::add);
    }

        public Optional<TransactionId> getId() {
        return id;
    }

        public BookYearId getBookYearId() {
        return bookYearId;
    }

        public LocalDate getDate() {
        return date;
    }

        public Stream<TransactionLine> getTransactionLines() {
        return transactionLines.stream();
    }

}
