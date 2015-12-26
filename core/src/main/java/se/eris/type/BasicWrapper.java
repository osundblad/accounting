package se.eris.type;

import org.jetbrains.annotations.NotNull;

public abstract class BasicWrapper<T> {

    @NotNull
    private final T type;

    protected BasicWrapper(@NotNull final T type) {
        this.type = type;
    }

    @NotNull
    public T raw() {
        return type;
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final BasicWrapper that = (BasicWrapper) o;

        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{'" + type + "'}";
    }

}
