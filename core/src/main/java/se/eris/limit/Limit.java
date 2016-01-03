package se.eris.limit;

import org.jetbrains.annotations.NotNull;

public interface Limit<T> {

    @NotNull
    ValidationMessages validate(@NotNull T item);

}
