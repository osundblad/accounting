package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.util.AccountingException;
import se.eris.accounting.util.ErrorCode;

public class NotFoundException extends AccountingException {
    public NotFoundException(@NotNull final String message) {
        super(message, ErrorCode.NOT_FOUND);
    }
}
