package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];

    public static Integer[] toObject(int[] array) {
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
    
    public static int[] toPrimitive(Integer[] array) {
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
    
    public static List<Integer> toList(int[] array) {
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
    public static boolean isIncreasing(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= array[i-1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 二维int数组转为为二维list
     * @param twoDArray
     * @return
     */
    public static List<List<Integer>> twoDArrayToList(int[][] twoDArray) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] array : twoDArray) {
            List<Integer> list = new ArrayList<>();
            for (int i : array) {
                list.add(i);
            }
            result.add(list);
        }
        return result;
    }
    
    /**
     * 合并两个有序数组
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2.clone();
        }
        if (nums2.length == 0) {
            return nums1.clone();
        }
        int[] result = new int[nums1.length + nums2.length];
        int nums1CurrentIndex = 0;
        int resultCurrentIndex = 0;
        for (int i = 0; i < nums2.length; i++) {
            int index = Math.abs(Arrays.binarySearch(nums1, nums1CurrentIndex, nums1.length, nums2[i])+1);
            System.arraycopy(nums1, nums1CurrentIndex, result, resultCurrentIndex, index-nums1CurrentIndex);
            resultCurrentIndex += (index-nums1CurrentIndex);
            nums1CurrentIndex += (index-nums1CurrentIndex);
            result[resultCurrentIndex] = nums2[i];
            resultCurrentIndex++;
        }
        // nums1有可能还没跑完
        if (nums1CurrentIndex < nums1.length) {
            System.arraycopy(nums1, nums1CurrentIndex, result, resultCurrentIndex, nums1.length-nums1CurrentIndex);
        }
        return result;
    }
    
}
