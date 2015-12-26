package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;

public class AccountInfo {
    @NotNull
    private final AccountCode code;
    @NotNull
    private final AccountName name;
    @NotNull
    private final AccountDescription description;

    public AccountInfo(@NotNull final AccountCode code, @NotNull final AccountName name, @NotNull final AccountDescription description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public AccountCode getCode() {
        return code;
    }

    @NotNull
    public AccountName getName() {
        return name;
    }

    @NotNull
    public AccountDescription getDescription() {
        return description;
    }

}
