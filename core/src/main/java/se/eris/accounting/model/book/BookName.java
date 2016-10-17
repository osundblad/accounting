package se.eris.accounting.model.book;

import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public class BookName extends StringWrapper {

    public static final int MAX_LENGTH = 40;

    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(1, MAX_LENGTH).build();

    public static BookName of(final String name) {
        return new BookName(name);
    }

    private BookName(final String name) {
        super(LIMITED_STRING.of(name.trim()));
    }

}
