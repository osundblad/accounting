package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public class AccountDescription extends StringWrapper {

    public static final int MAX_LENGTH = 1000;

    private static final @NotNull LimitedString LIMITED_STRING = LimitedString.init()
            .length(MAX_LENGTH).build();

    public static AccountDescription empty() {
        return from("");
    }

    public static AccountDescription from(@NotNull final String description) {
        return new AccountDescription(description);
    }

    private AccountDescription(@NotNull final String description) {
        super(LIMITED_STRING.of(description));
    }
}
