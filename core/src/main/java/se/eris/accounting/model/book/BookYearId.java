package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class BookYearId extends UUIDWrapper {

    @NotNull
    public static BookYearId from(@NotNull final String name) {
        return from(UUID.fromString(name));
    }

    @NotNull
    public static BookYearId from(@NotNull final UUID uuid) {
        return new BookYearId(uuid);
    }

    @NotNull
    public static BookYearId random() {
        return from(UUID.randomUUID());
    }

    private BookYearId(@NotNull final UUID uuid) {
        super(uuid);
    }
}
