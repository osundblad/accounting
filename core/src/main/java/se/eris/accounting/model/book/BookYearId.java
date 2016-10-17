package se.eris.accounting.model.book;

import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class BookYearId extends UUIDWrapper {

    public static BookYearId from(final String name) {
        return from(UUID.fromString(name));
    }

    public static BookYearId from(final UUID uuid) {
        return new BookYearId(uuid);
    }

    public static BookYearId random() {
        return from(UUID.randomUUID());
    }

    private BookYearId(final UUID uuid) {
        super(uuid);
    }
}
