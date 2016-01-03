package se.eris.limit;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ValidationMessages {

    @NotNull
    public static ValidationMessages of(@NotNull final String message) {
        return ValidationMessages.of(ValidationMessage.of(message));
    }

    @NotNull
    public static ValidationMessages of(@NotNull final ValidationMessage... messages) {
        return new ValidationMessages(Arrays.asList(messages));
    }

    @NotNull
    public static ValidationMessages of(final List<ValidationMessage> messages) {
        return new ValidationMessages(messages);
    }

    @NotNull
    public static ValidationMessages of(final Collection<ValidationMessage> messages) {
        return new ValidationMessages(messages);
    }

    @NotNull
    public static ValidationMessages empty() {
        return of();
    }

    @NotNull
    private final List<ValidationMessage> messages;


    private ValidationMessages(@NotNull final Collection<ValidationMessage> messages) {
        this.messages = new ArrayList<>(messages);
    }

    public boolean hasMessages() {
        return !messages.isEmpty();
    }

    @NotNull
    public Stream<ValidationMessage> getMessages() {
        return messages.stream();
    }

}
