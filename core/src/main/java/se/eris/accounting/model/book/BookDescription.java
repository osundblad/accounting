package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.limit.LimitedString;
import se.eris.jtype.type.StringWrapper;

public class BookDescription extends StringWrapper {

    @SuppressWarnings("WeakerAccess")
    public static final int MAX_LENGTH = 1000;

    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(MAX_LENGTH).build();

    @NotNull
    public static BookDescription of(@NotNull final String description) {
        return new BookDescription(description);
    }

    @NotNull
    public static BookDescription empty() {
        return new BookDescription("");
    }

    private BookDescription(@NotNull final String description) {
        super(LIMITED_STRING.of(description));
    }

}
