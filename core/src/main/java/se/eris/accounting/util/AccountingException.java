package se.eris.accounting.util;

import org.jetbrains.annotations.NotNull;

public class AccountingException extends RuntimeException {
    @NotNull
    private final ErrorCode errorCode;

    public AccountingException(@NotNull final String message, @NotNull final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AccountingException(@NotNull final String message, @NotNull final ErrorCode errorCode, @NotNull final Exception cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @NotNull
    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
