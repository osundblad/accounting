package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public final class BookId extends UUIDWrapper {

    @NotNull
    public static BookId from(@NotNull final String uuid) {
        return from(UUID.fromString(uuid));
    }

    @NotNull
    public static BookId from(@NotNull final UUID uuid) {
        return new BookId(uuid);
    }

    @NotNull
    public static BookId random() {
        return from(UUID.randomUUID());
    }

    private BookId(@NotNull final UUID uuid) {
        super(uuid);
    }

}
