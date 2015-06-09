package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class AlreadyPersistedException extends AccountingException {

    public AlreadyPersistedException(@NotNull final String message) {
        super(message, ErrorCode.ALREADY_PERSISTED);
    }

}
