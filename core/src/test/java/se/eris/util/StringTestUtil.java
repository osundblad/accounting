package se.eris.util;

import org.jetbrains.annotations.NotNull;

public class StringTestUtil {

    @NotNull
    public static String createLongString(final int length) {
        final String str = "some more text again, ";
        final StringBuilder sb = new StringBuilder(length + str.length());
        while (sb.length() <= length) {
            sb.append(str);
        }
        return sb.toString().substring(0, length);
    }
}
