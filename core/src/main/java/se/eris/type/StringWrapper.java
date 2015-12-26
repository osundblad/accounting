package se.eris.type;

import org.jetbrains.annotations.NotNull;

public abstract class StringWrapper extends BasicWrapper<String> {

    protected StringWrapper(@NotNull final String s) {
        super(s);
    }

    @NotNull
    public String asString() {
        return super.raw();
    }
}
