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

    /**
     * Gets a CharSequence length or {@code 0} if the CharSequence is
     * {@code null}.
     *
     * @param cs a CharSequence or {@code null}
     * @return CharSequence length or {@code 0} if the CharSequence is
     * {@code null}.
     * @since 2.4
     * @since 3.0 Changed signature from length(String) to length(CharSequence)
     */
    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
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

    /**
     * <p>Check if a CharSequence ends with a specified suffix (optionally case insensitive).</p>
     *
     * @param str        the CharSequence to check, may be null
     * @param suffix     the suffix to find, may be null
     * @param ignoreCase indicates whether the compare should ignore case
     *                   (case insensitive) or not.
     * @return {@code true} if the CharSequence starts with the prefix or
     * both {@code null}
     * @see java.lang.String#endsWith(String)
     */
    private static boolean endsWith(final CharSequence str, final CharSequence suffix, final boolean ignoreCase) {
        if (str == null || suffix == null) {
            return str == suffix;
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        final int strOffset = str.length() - suffix.length();
        return CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    /**
     * Appends the suffix to the end of the string if the string does not already end with the suffix.
     *
     * @param str        The string.
     * @param suffix     The suffix to append to the end of the string.
     * @param ignoreCase Indicates whether the compare should ignore case.
     * @param suffixes   Additional suffixes that are valid terminators (optional).
     * @return A new String if suffix was appended, the same string otherwise.
     */
    private static String appendIfMissing(final String str, final CharSequence suffix, final boolean ignoreCase, final CharSequence... suffixes) {
        if (str == null || isEmpty(suffix) || endsWith(str, suffix, ignoreCase)) {
            return str;
        }
        if (ArrayUtils.isNotEmpty(suffixes)) {
            for (final CharSequence s : suffixes) {
                if (endsWith(str, s, ignoreCase)) {
                    return str;
                }
            }
        }
        return str + suffix.toString();
    }
}
