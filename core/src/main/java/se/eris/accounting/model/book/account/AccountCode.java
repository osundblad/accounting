package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.util.LimitedString;

public class AccountCode {

    private static final int MAX_LENGTH = 12;
    private static final String MUST_MATCH = "[0-9a-zA-Z]+";
    private static final LimitedString LIMITED_STRING = LimitedString.init().length(1, MAX_LENGTH).matches(MUST_MATCH).build();

    public static AccountCode of(@NotNull final String code) {
        return new AccountCode(code);
    }

    @NotNull
    private final String code;

    private AccountCode(@NotNull final String code) {
        this.code = LIMITED_STRING.of(code);
    }

    @NotNull
    public String asString() {
        return code;
    }

}
