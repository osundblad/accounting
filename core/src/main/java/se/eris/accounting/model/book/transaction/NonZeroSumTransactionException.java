package se.eris.accounting.model.book.transaction;

import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class NonZeroSumTransactionException extends AccountingException {

        private final Transaction transaction;

    public NonZeroSumTransactionException(final Transaction transaction) {
        super("Non zero sum transaction", ErrorCode.TRANSACTION);
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "NonZeroSumTransactionException{transaction=" + transaction + '}';
    }

}
