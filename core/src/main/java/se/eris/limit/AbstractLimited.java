package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractLimited<T> {

    @NotNull
    private final ValidationBehavior validationBehavior;

    @NotNull
    private final List<Function<T, ValidationMessages>> limits;

    protected AbstractLimited(@NotNull final List<Function<T, ValidationMessages>> limits, @NotNull final ValidationBehavior validationBehavior) {
        this.limits = limits;
        //  * interface -> function (this.limits = limits.stream().map(limit -> (Function<T, ValidationMessages>) t -> limit.validate(t)).collect(Collectors.toList());)
        //  * sql.Date -> LocalDate (try UUID -> String)
        //  * ResponseEntity<T>
        //  * final Rest classes @JsonCreator&/@JsonProperty
        //  * Optional.map (transaction.getId().map(TransactionId::asUUID).orElse(null) and Optional.ofNullable(id).map(TransactionId::from))
        this.validationBehavior = validationBehavior;
    }

    @NotNull
    public T of(@NotNull final T t) {
        final ValidationBehavior behavior = validationBehavior.instance();
        for (final Function<T, ValidationMessages> limit : limits) {
            behavior.atValidation(limit.apply(t));
        }
        behavior.afterValidation();
        return t;
    }

    public abstract static class Builder<T> {

        @NotNull
        protected final List<Function<T, ValidationMessages>> limits = new ArrayList<>();

        @NotNull
        protected ValidationBehavior validationBehavior = new ValidationBehaviorThrowImmediately();

        @NotNull
        public final Builder<T> limit(@NotNull final Limit<T> limit) {
            limits.add(limit::validate);
            return this;
        }

        @NotNull
        public final Builder<T> limit(@NotNull final Function<T, ValidationMessages> limit) {
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
