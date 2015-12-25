package se.eris.type;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class UUIDWrapper {

    @NotNull
    protected final UUID uuid;

    protected UUIDWrapper() {
        this(UUID.randomUUID());
    }

    protected UUIDWrapper(@NotNull final String name) {
        this(UUID.fromString(name));
    }

    protected UUIDWrapper(@NotNull final UUID uuid) {
        this.uuid = uuid;
    }

    @NotNull
    public UUID asUUID() {
        return uuid;
    }

    @NotNull
    public String asString() {
        return uuid.toString();
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final UUIDWrapper that = (UUIDWrapper) o;

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
