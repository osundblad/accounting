package se.eris.accounting.model.book;

import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class BookId extends UUIDWrapper {

    public static BookId from(final String uuid) {
        return from(UUID.fromString(uuid));
    }

    public static BookId from(final UUID uuid) {
        return new BookId(uuid);
    }

    public static BookId random() {
        return from(UUID.randomUUID());
    }

    private BookId(final UUID uuid) {
        super(uuid);
    }

}
