package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class PairWrapperTest {


    @Test
    public void rawFirst() {
        assertThat(PairWrapperA.of("a", "b").rawFirst(), is("a"));
        assertThat(PairWrapperA.of("a", "b").rawSecond(), is("b"));
    }

    @Test
    public void hashCode_shouldWork() {
        assertThat(PairWrapperA.of("a", "b").hashCode(), is(PairWrapperA.of("a", "b").hashCode()));
        assertThat(PairWrapperA.of("a", "b").hashCode(), not(PairWrapperA.of("b", "a").hashCode()));
    }

    @Test
    public void equals_shouldWork() {
        final PairWrapperA a = PairWrapperA.of("a", "b");
        assertThat(a, is(a));
        assertThat(a, is(PairWrapperA.of("a", "b")));

        assertThat(a, not(PairWrapperA.of("b", "a")));
    }

    @Test
    public void equals_differentStringWrapperClasses_shouldDiffer() {
        final PairWrapperA a = PairWrapperA.of("a", "b");
        final PairWrapperB b = PairWrapperB.of("a", "b");

        assertThat(a, not(b));
    }

    @Test
    public void toString_showsClassName() {
        assertThat(PairWrapperA.of("a", "b").toString(), is("PairWrapperA{a, b}"));
    }

    private static class PairWrapperA extends PairWrapper<String> {

        private static PairWrapperA of(@NotNull final String s1, @NotNull final String s2) {
            return new PairWrapperA(s1, s2);
        }

        private PairWrapperA(@NotNull final String s1, @NotNull final String s2) {
            super(s1, s2);
        }
    }

    private static class PairWrapperB extends PairWrapper<String> {

        private static PairWrapperB of(@NotNull final String s1, @NotNull final String s2) {
            return new PairWrapperB(s1, s2);
        }

        private PairWrapperB(@NotNull final String s1, @NotNull final String s2) {
            super(s1, s2);
        }
    }

}