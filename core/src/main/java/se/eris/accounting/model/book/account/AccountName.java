package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.type.StringWrapper;
import se.eris.util.LimitedString;

public final class AccountName extends StringWrapper {

    private static final int MAX_LENGTH = 30;
    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init().length(1, MAX_LENGTH).build();

    @NotNull
    public static AccountName of(@NotNull final String name) {
        return new AccountName(name);
    }

    private AccountName(@NotNull final String name) {
        super(LIMITED_STRING.of(name));
    }

}
