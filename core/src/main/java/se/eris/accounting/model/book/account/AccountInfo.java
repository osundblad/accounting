package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;

public class AccountInfo {

    public static AccountInfo of(@NotNull final AccountClass accountClass, @NotNull final AccountCode code, @NotNull final AccountName name, @NotNull final AccountDescription description) {
        return new AccountInfo(accountClass, code, name, description);
    }

    @NotNull
    private final AccountClass accountClass;

    @NotNull
    private final AccountCode code;

    @NotNull
    private final AccountName name;

    @NotNull
    private final AccountDescription description;

    private AccountInfo(@NotNull final AccountClass accountClass, @NotNull final AccountCode code, @NotNull final AccountName name, @NotNull final AccountDescription description) {
        this.accountClass = accountClass;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public AccountClass getAccountClass() {
        return accountClass;
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
