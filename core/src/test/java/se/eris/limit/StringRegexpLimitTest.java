package se.eris.limit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringRegexpLimitTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_matches() {
        final Limit<String> limit = StringRegexpLimit.of(Pattern.compile("ab?c*d+"));

        limit.validate("ad");
        limit.validate("abd");
        limit.validate("accccd");
        limit.validate("addddd");
    }

    @Test
    public void validate_toLittle() {
        final Limit<String> limit = StringRegexpLimit.of(Pattern.compile("ab?c*d+"));

        assertThat(limit.validate("d").hasMessages(), is(true));
    }

    @Test
    public void validate_toMuch() {
        final Limit<String> limit = StringRegexpLimit.of(Pattern.compile("ab?c*d+"));

        assertThat(limit.validate("aad").hasMessages(), is(true));
    }

}