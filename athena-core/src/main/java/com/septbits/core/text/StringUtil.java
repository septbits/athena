package com.septbits.core.text;

/**
 * @author holten
 * @since 2022/6/19
 */
public class StringUtil {
    /**
     * Returns the string if it is not null, or an empty string otherwise.
     *
     * @param string the string to test and possibly return
     * @return {@code string} if it is not null; {@code ""} otherwise
     */
    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }
}
