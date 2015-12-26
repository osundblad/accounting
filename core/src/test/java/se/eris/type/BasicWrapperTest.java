package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class BasicWrapperTest {

    @Test
    public void raw() {
        assertThat(BasicWrapperA.of("a").raw(), is("a"));
        assertThat(BasicWrapperA.of("123").raw(), is("123"));
    }

    @Test
    public void hashCode_shouldWork() {
        assertThat(BasicWrapperA.of("a").hashCode(), is(BasicWrapperA.of("a").hashCode()));
    }

    @Test
    public void equals_shouldWork() {
        final BasicWrapperA a = BasicWrapperA.of("a");
        assertThat(a, is(a));
        assertThat(a, is(BasicWrapperA.of("a")));

        assertThat(a, not(BasicWrapperA.of("b")));
    }

    @Test
    public void equals_differentStringWrapperClasses_shouldDiffer() {
        final BasicWrapperA a = BasicWrapperA.of("a");
        final BasicWrapperB b = BasicWrapperB.of("a");

        assertThat(a, not(b));
    }

    @Test
    public void toString_showsClassName() {
        assertThat(BasicWrapperA.of("a").toString(), is("BasicWrapperA{a}"));
    }

    private static class BasicWrapperA extends BasicWrapper<String> {

        private static BasicWrapperA of(@NotNull final String s) {
            return new BasicWrapperA(s);
        }

        private BasicWrapperA(@NotNull final String s) {
            super(s);
        }
    }

    private static class BasicWrapperB extends BasicWrapper<String> {

        private static BasicWrapperB of(@NotNull final String s) {
            return new BasicWrapperB(s);
        }

        private BasicWrapperB(@NotNull final String s) {
            super(s);
        }
    }

}