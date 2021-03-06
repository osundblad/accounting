package se.eris.accounting.model.book.transaction;

import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class TransactionLineId extends UUIDWrapper {

        public static TransactionLineId from(final String name) {
        return from(UUID.fromString(name));
    }

        public static TransactionLineId from(final UUID uuid) {
        return new TransactionLineId(uuid);
    }

        public static TransactionLineId random() {
        return from(UUID.randomUUID());
    }

    private TransactionLineId(final UUID uuid) {
        super(uuid);
    }

}
