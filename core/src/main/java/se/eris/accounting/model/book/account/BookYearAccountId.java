package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class BookYearAccountId extends UUIDWrapper {

    @NotNull
    public static BookYearAccountId from(@NotNull final String name) {
        return from(UUID.fromString(name));
    }

    @NotNull
    public static BookYearAccountId from(@NotNull final UUID uuid) {
        return new BookYearAccountId(uuid);
    }

    @NotNull
    public static BookYearAccountId random() {
        return from(UUID.randomUUID());
    }

    private BookYearAccountId(@NotNull final UUID uuid) {
        super(uuid);
    }

}
