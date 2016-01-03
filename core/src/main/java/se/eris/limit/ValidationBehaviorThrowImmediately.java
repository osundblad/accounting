package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public class ValidationBehaviorThrowImmediately implements ValidationBehavior {

    @NotNull
    @Override
    public ValidationBehavior instance() {
        return new ValidationBehaviorThrowImmediately();
    }

    @Override
    public void atValidation(@NotNull final ValidationMessages messages) {
        if (messages.hasMessages()) {
            throw new ValidationException(messages);
        }
    }

    @Override
    public void afterValidation() {
    }

}
