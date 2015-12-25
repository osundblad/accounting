package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.type.UUIDOptionalWrapper;

import java.util.Optional;
import java.util.UUID;

public class BookId extends UUIDOptionalWrapper {

    @NotNull
    public static BookId of(@NotNull final Optional<UUID> id) {
        return new BookId(id);
    }

    @NotNull
    public static BookId of(@Nullable final UUID id) {
        return new BookId(Optional.ofNullable(id));
    }

    @NotNull
    public static BookId empty() {
        return new BookId(Optional.empty());
    }

    private BookId(@NotNull final Optional<UUID> uuid) {
        super(uuid);
    }
}
