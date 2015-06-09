package org.jetbrains.annotations;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("ConstantConditions")
public class NotNullTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void notNullArgument_shouldInsertNullCheck() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("@NotNull");
        notNullArgument(null);
    }

    private void notNullArgument(@NotNull final String s) {
    }

    @Test
    public void notNullReturnValue_shouldInsertNullCheck() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("@NotNull");
        notNullReturnValue();
    }

    @NotNull
    private String notNullReturnValue() {
        return null;
    }

}
