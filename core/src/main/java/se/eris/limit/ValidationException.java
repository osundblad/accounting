package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public class ValidationException extends IllegalArgumentException {

    @NotNull
    private final ValidationMessages messages;

    public ValidationException(@NotNull final ValidationMessages messages) {
        this.messages = messages;
    }

    @NotNull
    public ValidationMessages getMessages() {
        return messages;
    }

}
