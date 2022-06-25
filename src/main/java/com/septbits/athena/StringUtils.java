package com.septbits.athena;

/**
 * @author holten
 * @since 2022/6/19
 */
public class StringUtils {
    /**
     * The empty String {@code ""}.
     */
    public static final String EMPTY = "";
    public static final String DEFAULT_ABBREV_MARKER = "...";

    /**
     * Returns the string if it is not null, or an empty string otherwise.
     *
     * @param string the string to test and possibly return
     * @return {@code string} if it is not null; {@code ""} otherwise
     */
    public static String nullToEmpty(final String string) {
        return string == null ? "" : string;
    }

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     *
     * @param string the string to test and possibly return
     * @return {@code string} itself if it is nonempty; {@code null} if it is empty or null
     */
    public static String emptyToNull(final String string) {
        return isEmpty(string) ? null : string;
    }

    /**
     * Returns {@code true} if the given charSequence is null or is the empty charSequence.
     *
     * @param charSequence a charSequence reference to check
     * @return {@code true} if the charSequence is null or is the empty charSequence
     */
    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // Negative start and end positions can be used to specify offsets relative to the end of the String.
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    public static String abbreviate(final String str, final int maxWidth) {
        return abbreviate(str, DEFAULT_ABBREV_MARKER, 0, maxWidth);
    }

    public static String abbreviate(final String str, final int offset, final int maxWidth) {
        return abbreviate(str, DEFAULT_ABBREV_MARKER, offset, maxWidth);
    }

    public static String abbreviate(final String str, final String abbrevMarker, final int maxWidth) {
        return abbreviate(str, abbrevMarker, 0, maxWidth);
    }

    public static String abbreviate(final String str, final String abbrevMarker, int offset, final int maxWidth) {
        if (isNotEmpty(str) && EMPTY.equals(abbrevMarker) && maxWidth > 0) {
            return substring(str, 0, maxWidth);
        }
        if (isEmpty(str) || isEmpty(abbrevMarker)) {
            return str;
        }
        final int abbrevMarkerLength = abbrevMarker.length();
        final int minAbbrevWidth = abbrevMarkerLength + 1;
        final int minAbbrevWidthOffset = abbrevMarkerLength + abbrevMarkerLength + 1;

        if (maxWidth < minAbbrevWidth) {
            throw new IllegalArgumentException(String.format("Minimum abbreviation width is %d", minAbbrevWidth));
        }
        final int strLen = str.length();
        if (strLen <= maxWidth) {
            return str;
        }
        if (offset > strLen) {
            offset = strLen;
        }
        if (strLen - offset < maxWidth - abbrevMarkerLength) {
            offset = strLen - (maxWidth - abbrevMarkerLength);
        }
        if (offset <= abbrevMarkerLength + 1) {
            return str.substring(0, maxWidth - abbrevMarkerLength) + abbrevMarker;
        }
        if (maxWidth < minAbbrevWidthOffset) {
            throw new IllegalArgumentException(String.format("Minimum abbreviation width with offset is %d", minAbbrevWidthOffset));
        }
        if (offset + maxWidth - abbrevMarkerLength < strLen) {
            return abbrevMarker + abbreviate(str.substring(offset), abbrevMarker, maxWidth - abbrevMarkerLength);
        }
        return abbrevMarker + str.substring(strLen - (maxWidth - abbrevMarkerLength));
    }

    public static String abbreviateMiddle(final String str, final String middle, final int length) {
        if (isEmpty(str) || isEmpty(middle) || length >= str.length() || length < middle.length() + 2) {
            return str;
        }

        final int targetSting = length - middle.length();
        final int startOffset = targetSting / 2 + targetSting % 2;
        final int endOffset = str.length() - targetSting / 2;

        return str.substring(0, startOffset) +
                middle +
                str.substring(endOffset);
    }
}
