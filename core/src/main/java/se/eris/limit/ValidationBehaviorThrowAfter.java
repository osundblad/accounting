package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationBehaviorThrowAfter implements ValidationBehavior {

    @NotNull
    private final List<ValidationMessage> validationMessages = new ArrayList<>();

    @NotNull
    @Override
    public ValidationBehavior instance() {
        return new ValidationBehaviorThrowAfter();
    }

    @Override
    public void atValidation(@NotNull final ValidationMessages messages) {
        if (messages.hasMessages()) {
            messages.getMessages().collect(Collectors.toList());
        }
    }

    @Override
    public void after() {
        if (!validationMessages.isEmpty()) {
            throw new ValidationException(ValidationMessages.of(validationMessages));
        }
    }


}
