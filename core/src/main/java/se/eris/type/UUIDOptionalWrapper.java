package se.eris.type;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public abstract class UUIDOptionalWrapper {

    @NotNull
    protected final Optional<UUID> uuid;

    protected UUIDOptionalWrapper(@NotNull final Optional<UUID> uuid) {
        this.uuid = uuid;
    }

    @NotNull
    public Optional<UUID> asOptional() {
        return uuid;
    }

    public boolean isPresent() {
        return uuid.isPresent();
    }

    @NotNull
    public UUID asUUID() {
        return uuid.get();
    }

    @NotNull
    public String asString() {
        return uuid.get().toString();
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final UUIDOptionalWrapper that = (UUIDOptionalWrapper) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{'" + uuid + "'}";
    }

}
