package se.eris.accounting.model.book.account;

import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public final class AccountName extends StringWrapper {

    public static final int MAX_LENGTH = 30;

    private static final LimitedString LIMITED_STRING = LimitedString.init().length(1, MAX_LENGTH).build();

    public static AccountName from(final String name) {
        return new AccountName(name);
    }

    private AccountName(final String name) {
        super(LIMITED_STRING.of(name.trim()));
    }

}
