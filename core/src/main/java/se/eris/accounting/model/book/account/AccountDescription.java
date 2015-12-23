package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.util.LimitedString;

public class AccountDescription {

    private static final int MAX_LENGTH = 1000;
    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(MAX_LENGTH).build();

    @NotNull
    public static AccountDescription of(@NotNull final String name) {
        return new AccountDescription(name);
    }

    @NotNull
    private final String description;

    private AccountDescription(@NotNull final String description) {
        this.description = LIMITED_STRING.of(description);
    }

    @NotNull
    public String asString() {
        return description;
    }

}
