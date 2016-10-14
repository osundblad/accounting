package se.eris.accounting.model.book.transaction;

import org.jetbrains.annotations.NotNull;
import se.eris.jtype.type.UUIDWrapper;

import java.util.UUID;

public final class TransactionLineId extends UUIDWrapper {

    @NotNull
    public static TransactionLineId from(@NotNull final String name) {
        return from(UUID.fromString(name));
    }

    @NotNull
    public static TransactionLineId from(@NotNull final UUID uuid) {
        return new TransactionLineId(uuid);
    }

    @NotNull
    public static TransactionLineId random() {
        return from(UUID.randomUUID());
    }

    private TransactionLineId(@NotNull final UUID uuid) {
        super(uuid);
    }

}
