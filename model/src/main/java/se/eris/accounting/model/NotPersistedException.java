package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class NotPersistedException extends AccountingException {

    public NotPersistedException(@NotNull final String message) {
        super(message, ErrorCode.NOT_PERSISTED);
    }

    public NotPersistedException(@NotNull final Object o) {
        super(o.toString() + " not persisted", ErrorCode.NOT_PERSISTED);
    }

}
