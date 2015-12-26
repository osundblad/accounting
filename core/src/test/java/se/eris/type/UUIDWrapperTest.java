package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UUIDWrapperTest {

    @Test
    public void asUUID() {
        final UUID uuid = UUID.randomUUID();
        final UUIDWrapperA wrapper = new UUIDWrapperA(uuid);

        assertThat(wrapper.asUUID(), is(uuid));
    }

    @Test
    public void asString() {
        final UUID uuid = UUID.randomUUID();
        final UUIDWrapperA wrapper = new UUIDWrapperA(uuid);

        assertThat(wrapper.asString(), is(uuid.toString()));
    }

    private static class UUIDWrapperA extends UUIDWrapper {

        private UUIDWrapperA(@NotNull final UUID uuid) {
            super(uuid);
        }

    }
}