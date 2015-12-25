package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.type.StringWrapper;
import se.eris.util.LimitedString;

public class AccountDescription extends StringWrapper {

    public static final int MAX_LENGTH = 1000;

    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(MAX_LENGTH).build();

    @NotNull
    public static AccountDescription of(@NotNull final String description) {
        return new AccountDescription(description);
    }

    private AccountDescription(@NotNull final String description) {
        super(LIMITED_STRING.of(description));
    }

}
