package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class NonZeroSumTransactionException extends AccountingException {

        private final Transaction transaction;

    public NonZeroSumTransactionException(@NotNull final Transaction transaction) {
        super("Non zero sum transaction", ErrorCode.TRANSACTION);
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "NonZeroSumTransactionException{transaction=" + transaction + '}';
    }

}
