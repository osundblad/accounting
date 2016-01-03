package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public interface ValidationBehavior {

    @NotNull
    ValidationBehavior instance();

    void atValidation(@NotNull ValidationMessages messages);

    void after();

}
