package se.eris.accounting.model.book.account;

import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class BookYearAccountId extends UUIDWrapper {

        public static BookYearAccountId from(final String name) {
        return from(UUID.fromString(name));
    }

        public static BookYearAccountId from(final UUID uuid) {
        return new BookYearAccountId(uuid);
    }

        public static BookYearAccountId random() {
        return from(UUID.randomUUID());
    }

    private BookYearAccountId(final UUID uuid) {
        super(uuid);
    }

}
