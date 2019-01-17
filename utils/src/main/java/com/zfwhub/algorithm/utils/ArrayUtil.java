package com.zfwhub.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];

    public static Integer[] toObject(final int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.valueOf(array[i]);
        }
        return result;
    }
    
    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].intValue();
        }
        return result;
    }
    
    /**
     * 初始化包含1到n的数组。
     * @param n
     * @return
     */
    public static int[] newIntArray(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        final int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = i + 1;
        }
        return result;
    }
    
    public static List<Integer> toList(final int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new ArrayList<>();
        }
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
           result.add(Integer.valueOf(array[i]));
        }
        return result;
    }
    
    /**
     * 判断数组是否严格递增。
     * @param array
     * @return
     */
    public static boolean isIncreasing(final int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= array[i-1]) {
                return false;
            }
        }
        return true;
    }

}
