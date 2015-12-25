package se.eris.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.limit.Limit;
import se.eris.limit.StringRegexpLimit;

import java.util.regex.Pattern;

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

        exception.expect(IllegalArgumentException.class);
        limit.validate("d");
    }

    @Test
    public void validate_toMuch() {
        final Limit<String> limit = StringRegexpLimit.of(Pattern.compile("ab?c*d+"));

        exception.expect(IllegalArgumentException.class);
        limit.validate("aad");
    }

}