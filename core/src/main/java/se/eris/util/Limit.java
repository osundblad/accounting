package se.eris.util;

import org.jetbrains.annotations.NotNull;

public interface Limit<T> {

    void validate(@NotNull T item);

}