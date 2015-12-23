package se.eris.util;

import org.jetbrains.annotations.NotNull;

public interface StringLimit extends Limit<String> {

    void validate(@NotNull String item);

}
