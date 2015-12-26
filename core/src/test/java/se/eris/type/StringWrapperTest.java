package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringWrapperTest {

    @Test
    public void asString() {
        assertThat(StringWrapperA.of("a").asString(), is("a"));
        assertThat(StringWrapperA.of("123").asString(), is("123"));
    }

    private static class StringWrapperA extends StringWrapper {

        private static StringWrapperA of(@NotNull final String s) {
            return new StringWrapperA(s);
        }

        private StringWrapperA(@NotNull final String s) {
            super(s);
        }
    }

}