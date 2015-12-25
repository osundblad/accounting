package se.eris.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbstractLimited<T> {

    @NotNull
    private final List<? extends Limit<T>> limits;

    protected AbstractLimited(@NotNull final List<? extends Limit<T>> limits) {
        this.limits = limits;
    }

    @NotNull
    public T of(@NotNull final T t) {
        for (final Limit<T> limit : limits) {
            limit.validate(t);
        }
        return t;
    }

    public static class Builder<T> {

        @NotNull
        protected final List<Limit<T>> limits = new ArrayList<>();

        @NotNull
        public final Builder<T> limit(@NotNull final Limit<T> limit) {
            limits.add(limit);
            return this;
        }

    }
}
