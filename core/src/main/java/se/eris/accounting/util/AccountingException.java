package se.eris.accounting.util;

public class AccountingException extends RuntimeException {

    private final ErrorCode errorCode;

    public AccountingException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AccountingException(final String message, final ErrorCode errorCode, final Exception cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
