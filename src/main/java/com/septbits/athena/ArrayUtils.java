package com.septbits.athena;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author holten
 * @since 2022/7/2
 */
public class ArrayUtils {

    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};

    /**
     * An empty immutable {@link Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = {};

    /**
     * An empty immutable {@link Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = {};

    /**
     * An empty immutable {@link Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@link Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = {};

    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = {};

    /**
     * An empty immutable {@link Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@link Field} array.
     *
     * @since 3.10
     */
    public static final Field[] EMPTY_FIELD_ARRAY = {};

    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = {};

    /**
     * An empty immutable {@link Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = {};

    /**
     * An empty immutable {@link Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = {};

    /**
     * An empty immutable {@link Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@link Method} array.
     *
     * @since 3.10
     */
    public static final Method[] EMPTY_METHOD_ARRAY = {};

    /**
     * An empty immutable {@link Object} array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = {};

    /**
     * An empty immutable {@link Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = {};

    /**
     * An empty immutable {@link String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = {};

    /**
     * An empty immutable {@link Throwable} array.
     *
     * @since 3.10
     */
    public static final Throwable[] EMPTY_THROWABLE_ARRAY = {};

    /**
     * An empty immutable {@link Type} array.
     *
     * @since 3.10
     */
    public static final Type[] EMPTY_TYPE_ARRAY = {};

    /**
     * The index value when an element is not found in a list or array: {@code -1}.
     * This value is returned by methods in this class and can also be used in comparisons with values returned by
     * various method from {@link java.util.List}.
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * Returns the length of the specified array.
     * This method can deal with {@link Object} arrays and with primitive arrays.
     * <p>
     * If the input array is {@code null}, {@code 0} is returned.
     * </p>
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array the array to retrieve the length from, may be null
     * @return The length of the array, or {@code 0} if the array is {@code null}
     * @throws IllegalArgumentException if the object argument is not an array.
     * @since 2.1
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * Checks if an array of Objects is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * Checks if an array of Objects is not empty and not {@code null}.
     *
     * @param <T>   the component type of the array
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

    /**
     * ArrayUtils instances should NOT be constructed in standard programming.
     * Instead, the class should be used as {@code ArrayUtils.clone(new int[] {2})}.
     * <p>
     * This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     * </p>
     */
    public ArrayUtils() {
    }
}
