package se.eris.accounting.model.book;

import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public class BookDescription extends StringWrapper {

    @SuppressWarnings("WeakerAccess")
    public static final int MAX_LENGTH = 1000;

    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(MAX_LENGTH).build();

    public static BookDescription of(final String description) {
        return new BookDescription(description);
    }

    public static BookDescription empty() {
        return new BookDescription("");
    }

    private BookDescription(final String description) {
        super(LIMITED_STRING.of(description));
    }

}
