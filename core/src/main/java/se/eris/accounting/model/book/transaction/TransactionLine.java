package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.account.BookYearAccountId;

import java.util.Optional;

public class TransactionLine {

        public static TransactionLine of(@NotNull final Optional<TransactionLineId> id, @NotNull final BookYearAccountId bookYearAccountId, @NotNull final Amount amount) {
        return new TransactionLine(id, bookYearAccountId, amount);
    }

        private final Optional<TransactionLineId> id;

        private final BookYearAccountId bookYearAccountId;

        private final Amount amount;

    private TransactionLine(@NotNull final Optional<TransactionLineId> id, @NotNull final BookYearAccountId bookYearAccountId, @NotNull final Amount amount) {
        this.id = id;
        this.bookYearAccountId = bookYearAccountId;
        this.amount = amount;
    }

        public Optional<TransactionLineId> getId() {
        return id;
    }

        public BookYearAccountId getBookYearAccountId() {
        return bookYearAccountId;
    }

        public Amount getAmount() {
        return amount;
    }

}
