package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.util.type.UUIDWrapper;

import java.util.UUID;

public class BookId extends UUIDWrapper {

    @NotNull
    public static BookId from(@NotNull final String name) {
        return from(UUID.fromString(name));
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
