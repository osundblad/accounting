package se.eris.accounting.persistence;

import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class AlreadyPersistedException extends AccountingException {

    public AlreadyPersistedException(final String message) {
        super(message, ErrorCode.ALREADY_PERSISTED);
    }

}
