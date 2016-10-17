package se.eris.accounting.persistence;

import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class NotPersistedException extends AccountingException {

    public NotPersistedException(final String s) {
        super(s, ErrorCode.NOT_PERSISTED);
    }
}
