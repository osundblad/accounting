package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class StringWrapperTest {

    @Test
    public void asString() {
        assertThat(StringWrapperA.of("a").asString(), is("a"));
        assertThat(StringWrapperA.of("123").asString(), is("123"));
    }

    @Test
    public void hashCode_shouldWork() {
        assertThat(StringWrapperA.of("a").hashCode(), is(StringWrapperA.of("a").hashCode()));
    }

    @Test
    public void equals_shouldWork() {
        final StringWrapperA a = StringWrapperA.of("a");
        assertThat(a, is(a));
        assertThat(a, is(StringWrapperA.of("a")));

        assertThat(a, not(StringWrapperA.of("b")));
    }

    @Test
    public void equals_differentStringWrapperClasses_shouldDiffer() {
        final StringWrapperA a = StringWrapperA.of("a");
        final StringWrapperB b = StringWrapperB.of("a");

        assertThat(a, not(b));
    }

    @Test
    public void toString_showsClassName() {
        assertThat(StringWrapperA.of("description").toString(), is("StringWrapperA{'description'}"));
    }

    private static class StringWrapperA extends StringWrapper {

        private static StringWrapperA of(@NotNull final String s) {
            return new StringWrapperA(s);
        }

        private StringWrapperA(@NotNull final String s) {
            super(s);
        }
    }

    private static class StringWrapperB extends StringWrapper {

        private static StringWrapperB of(@NotNull final String s) {
            return new StringWrapperB(s);
        }

        private StringWrapperB(@NotNull final String s) {
            super(s);
        }
    }

}