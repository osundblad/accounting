package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public interface StringLimit extends Limit<String> {

    @NotNull
    ValidationMessages validate(@NotNull String item);

}
