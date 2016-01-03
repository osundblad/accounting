package se.eris.limit;

import org.jetbrains.annotations.NotNull;
import se.eris.type.StringWrapper;

public class ValidationMessage extends StringWrapper {

    @NotNull
    public static ValidationMessage of(@NotNull final String s) {
        return new ValidationMessage(s);
    }

    private ValidationMessage(@NotNull final String s) {
        super(s);
    }

}
