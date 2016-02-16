package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.util.limit.LimitedString;
import se.eris.util.type.StringWrapper;

public class BookName extends StringWrapper {

    public static final int MAX_LENGTH = 40;

    private static final @NotNull LimitedString LIMITED_STRING = LimitedString.init()
            .length(1, MAX_LENGTH).build();

    @NotNull
    public static BookName of(@NotNull final String name) {
        return new BookName(name);
    }

    private BookName(@NotNull final String name) {
        super(LIMITED_STRING.of(name.trim()));
    }

}
