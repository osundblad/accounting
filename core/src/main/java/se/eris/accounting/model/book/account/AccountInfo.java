package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;

public class AccountInfo {
    @NotNull
    private final AccountCode code;
    @NotNull
    private final AccountName name;
    @NotNull
    private final AccountDescription description;

    public AccountInfo(@NotNull final String code, @NotNull final String name, @NotNull final String description) {
        this.code = AccountCode.of(code);
        this.name = AccountName.of(name);
        this.description = AccountDescription.of(description);
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
