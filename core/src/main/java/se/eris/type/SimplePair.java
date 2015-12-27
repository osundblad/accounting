package se.eris.type;

import org.jetbrains.annotations.NotNull;

public abstract class SimplePair<T> {

    @NotNull
    private final T first;
    @NotNull
    private final T second;

    protected SimplePair(@NotNull final T first, @NotNull final T second) {
        this.first = first;
        this.second = second;
    }

    @NotNull
    public T rawFirst() {
        return first;
    }

    @NotNull
    public T rawSecond() {
        return second;
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final SimplePair<?> that = (SimplePair<?>) o;

        return first.equals(that.first) && second.equals(that.second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = (31 * result) + second.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + first + ", " + second + "}";
    }

}
