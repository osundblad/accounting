package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.limit.LimitedString;
import se.eris.type.StringWrapper;

public class BookName extends StringWrapper {

    public static final int MAX_LENGTH = 40;

    @NotNull
    private static final LimitedString LIMITED_STRING = LimitedString.init()
            .length(1, MAX_LENGTH).build();

    @NotNull
    public static BookName of(@NotNull final String name) {
        return new BookName(name);
    }

    private BookName(@NotNull final String name) {
        super(LIMITED_STRING.of(name.trim()));
    }

}