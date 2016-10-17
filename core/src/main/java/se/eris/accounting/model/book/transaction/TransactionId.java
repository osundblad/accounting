package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public class TransactionId extends UUIDWrapper {

        public static TransactionId from(@NotNull final String name) {
        return from(UUID.fromString(name));
    }

        public static TransactionId from(@NotNull final UUID uuid) {
        return new TransactionId(uuid);
    }

        public static TransactionId random() {
        return from(UUID.randomUUID());
    }

    private TransactionId(@NotNull final UUID uuid) {
        super(uuid);
    }

}
