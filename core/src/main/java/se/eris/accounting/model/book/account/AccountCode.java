package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.util.limit.LimitedString;
import se.eris.util.type.StringWrapper;

public class AccountCode extends StringWrapper {

    public static final int MAX_LENGTH = 12;
    public static final String MUST_MATCH = "[0-9a-zA-Z]+";

    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(1, MAX_LENGTH)
            .matches(MUST_MATCH).build();

    public static AccountCode from(@NotNull final String code) {
        return new AccountCode(code);
    }

    private AccountCode(@NotNull final String code) {
        super(LIMITED_STRING.of(code));
    }

}
