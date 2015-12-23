package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.util.LimitedString;

public final class AccountName {

    private static final int MAX_LENGTH = 30;
    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init().length(1, MAX_LENGTH).build();

    @NotNull
    public static AccountName of(@NotNull final String name) {
        return new AccountName(name);
    }

    @NotNull
    private final String name;

    private AccountName(@NotNull final String name) {
        this.name = LIMITED_STRING.of(name);
    }

    @NotNull
    public String asString() {
        return name;
    }

}
