package se.eris.accounting.util;

public enum ErrorCode {

    /**
     * AccountingException (every error should have its own ErrorCode, you lazy bastard).
     */
    @SuppressWarnings("UnusedDeclaration")
    UNDEFINED,

    NOT_PERSISTED,
    ALREADY_PERSISTED,
    NOT_FOUND,

}
