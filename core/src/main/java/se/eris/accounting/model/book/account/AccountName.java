package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public final class AccountName extends StringWrapper {

    public static final int MAX_LENGTH = 30;

    private static final @NotNull LimitedString LIMITED_STRING = LimitedString.init().length(1, MAX_LENGTH).build();

    @NotNull
    public static AccountName from(@NotNull final String name) {
        return new AccountName(name);
    }

    private AccountName(@NotNull final String name) {
        super(LIMITED_STRING.of(name.trim()));
    }

}
