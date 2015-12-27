package se.eris.type;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SimplePairTest {

    @Test
    public void rawFirst() {
        assertThat(SimplePairA.of("a", "b").rawFirst(), is("a"));
        assertThat(SimplePairA.of("a", "b").rawSecond(), is("b"));
    }

    @Test
    public void hashCode_shouldWork() {
        assertThat(SimplePairA.of("a", "b").hashCode(), is(SimplePairA.of("a", "b").hashCode()));
        assertThat(SimplePairA.of("a", "b").hashCode(), not(SimplePairA.of("b", "a").hashCode()));
    }

    @Test
    public void equals_shouldWork() {
        final SimplePairA a = SimplePairA.of("a", "b");
        assertThat(a, is(a));
        assertThat(a, is(SimplePairA.of("a", "b")));

        assertThat(a, not(SimplePairA.of("b", "a")));
    }

    @Test
    public void equals_differentStringWrapperClasses_shouldDiffer() {
        final SimplePairA a = SimplePairA.of("a", "b");
        final SimplePairB b = SimplePairB.of("a", "b");

        assertThat(a, not(b));
    }

    @Test
    public void toString_showsClassName() {
        assertThat(SimplePairA.of("a", "b").toString(), is("SimplePairA{a, b}"));
    }

    private static class SimplePairA extends SimplePair<String> {

        private static SimplePairA of(@NotNull final String s1, @NotNull final String s2) {
            return new SimplePairA(s1, s2);
        }

        private SimplePairA(@NotNull final String s1, @NotNull final String s2) {
            super(s1, s2);
        }
    }

    private static class SimplePairB extends SimplePair<String> {

        private static SimplePairB of(@NotNull final String s1, @NotNull final String s2) {
            return new SimplePairB(s1, s2);
        }

        private SimplePairB(@NotNull final String s1, @NotNull final String s2) {
            super(s1, s2);
        }
    }

}