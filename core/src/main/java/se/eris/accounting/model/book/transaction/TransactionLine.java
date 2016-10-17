package se.eris.accounting.model.book.transaction;

import se.eris.accounting.model.book.account.BookYearAccountId;

import java.util.Optional;

public class TransactionLine {

        public static TransactionLine of(final Optional<TransactionLineId> id, final BookYearAccountId bookYearAccountId, final Amount amount) {
        return new TransactionLine(id, bookYearAccountId, amount);
    }

        private final Optional<TransactionLineId> id;

        private final BookYearAccountId bookYearAccountId;

        private final Amount amount;

    private TransactionLine(final Optional<TransactionLineId> id, final BookYearAccountId bookYearAccountId, final Amount amount) {
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
