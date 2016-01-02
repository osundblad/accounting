package se.eris.limit;

import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;

public class EmailLimit implements StringLimit {

    @NotNull
    public static EmailLimit advanced(final boolean allowLocal, final boolean allowTld) {
        return new EmailLimit(EmailValidator.getInstance(allowLocal, allowTld));
    }

    @NotNull
    public static EmailLimit advanced(final boolean allowLocal) {
        return new EmailLimit(EmailValidator.getInstance(allowLocal));
    }

    @NotNull
    public static EmailLimit simple() {
        return new EmailLimit(EmailValidator.getInstance());
    }

    @NotNull
    private final EmailValidator emailValidator;

    private EmailLimit(@NotNull final EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Override
    public void validate(@NotNull final String emailAddress) {
        if (!emailValidator.isValid(emailAddress)) {
            throw new IllegalArgumentException("'" + emailAddress + "' is not a does not valid email address");
        }
    }

}
