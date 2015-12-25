package se.eris.type;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class UUIDWrapper extends TypeWrapper<UUID> {

    protected UUIDWrapper(@NotNull final UUID uuid) {
        super(uuid);
    }

    @NotNull
    public UUID asUUID() {
        return super.raw();
    }

    @NotNull
    public String asString() { return this.asUUID().toString(); }

}
