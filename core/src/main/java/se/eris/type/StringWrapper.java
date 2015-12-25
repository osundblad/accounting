package se.eris.type;

import org.jetbrains.annotations.NotNull;

public abstract class StringWrapper {

    @NotNull
    protected final String s;

    protected StringWrapper(@NotNull final String s) {
        this.s = s;
    }

    @NotNull
    public String asString() {
        return s;
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final StringWrapper that = (StringWrapper) o;

        return s.equals(that.s);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{'" + s + "'}";
    }

}
