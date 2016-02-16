package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.util.limit.LimitedString;
import se.eris.util.type.StringWrapper;

public class BookDescription extends StringWrapper {

    public static final int MAX_LENGTH = 1000;

    private static final @NotNull LimitedString LIMITED_STRING = LimitedString.init()
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
