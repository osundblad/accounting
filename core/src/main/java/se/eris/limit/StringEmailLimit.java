package se.eris.limit;

import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;

public class StringEmailLimit implements StringLimit {

    @NotNull
    public static StringEmailLimit advanced(final boolean allowLocal, final boolean allowTld) {
        return new StringEmailLimit(EmailValidator.getInstance(allowLocal, allowTld));
    }

    @NotNull
    public static StringEmailLimit advanced(final boolean allowLocal) {
        return new StringEmailLimit(EmailValidator.getInstance(allowLocal));
    }

    @NotNull
    public static StringEmailLimit simple() {
        return new StringEmailLimit(EmailValidator.getInstance());
    }

    @NotNull
    private final EmailValidator emailValidator;

    private StringEmailLimit(@NotNull final EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final String emailAddress) {
        if (!emailValidator.isValid(emailAddress)) {
            return ValidationMessages.of("'" + emailAddress + "' is not a does not valid email address");
        }
        return ValidationMessages.empty();
    }

}
