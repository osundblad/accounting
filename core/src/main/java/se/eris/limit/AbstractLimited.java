package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLimited<T> {

    @NotNull
    private final ValidationBehavior validationBehavior;

    @NotNull
    private final List<? extends Limit<T>> limits;

    protected AbstractLimited(@NotNull final List<? extends Limit<T>> limits, @NotNull final ValidationBehavior validationBehavior) {
        this.limits = limits;
        this.validationBehavior = validationBehavior;
    }

    @NotNull
    public T of(@NotNull final T t) {
        final ValidationBehavior behavior = validationBehavior.instance();
        for (final Limit<T> limit : limits) {
            behavior.atValidation(limit.validate(t));
        }
        behavior.afterValidation();
        return t;
    }

    public abstract static class Builder<T> {

        @NotNull
        protected final List<Limit<T>> limits = new ArrayList<>();

        @NotNull
        protected ValidationBehavior validationBehavior = new ValidationBehaviorThrowImmediately();

        @NotNull
        public final Builder<T> limit(@NotNull final Limit<T> limit) {
            limits.add(limit);
            return this;
        }

        @NotNull
        public final Builder<T> limit(@NotNull final ValidationBehavior behavior) {
            this.validationBehavior = behavior;
            return this;
        }

    }

}
