package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;

public class AccountInfo {
    @NotNull
    private final String code;
    @NotNull
    private final String name;
    @NotNull
    private final String description;

    public AccountInfo(@NotNull final String code, @NotNull final String name, @NotNull final String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public String getCode() {
        return code;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

}
