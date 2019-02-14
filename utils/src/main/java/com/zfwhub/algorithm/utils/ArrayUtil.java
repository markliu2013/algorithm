package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];

    /**
     * <p>Converts an array of primitive ints to objects.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array  an {@code int} array
     * @return an {@code Integer} array, {@code null} if null array input
     */
    public static Integer[] toObject(int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.valueOf(array[i]);
        }
        return result;
    }
    
    /**
     * <p>Converts an array of object Integer to primitives handling {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array  a {@code Integer} array, may be {@code null}
     * @param valueForNull  the value to insert if {@code null} found
     * @return an {@code int} array, {@code null} if null array input
     */
    public static int[] toPrimitive(Integer[] array, int valueForNull) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            Integer b = array[i];
            result[i] = (b == null ? valueForNull : b.intValue());
        }
        return result;
    }
    
    /**
     * 初始化一个int数组，包含a到b的所有整数。
     * @param a inclusive
     * @param b inclusive
     * @return
     */
    public static int[] newIntArray(int a, int b) {
        if (a > b) throw new IllegalArgumentException("a > b");
        int[] result = new int[b-a+1];
        for (int i = 0; i < result.length; i++) {
            result[i] = a + i;
        }
        return result;
    }
    
    /**
     * int数组转list
     * @param array
     * @return
     */
    public static List<Integer> toList(int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
           result.add(Integer.valueOf(array[i]));
        }
        return result;
    }
    
    /**
     * 判断数组是否严格递增。空数组是严格递增。
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
    
    /**
     * 数组求和
     * @param nums
     * @return
     */
    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
    
    /**
     * 指定范围的数组求和。
     * @param nums
     * @param fromIndex inclusive
     * @param toIndex exclusive
     * @return
     */
    public static int sum(int[] nums, int fromIndex, int toIndex) {
        Utilities.indexRangeCheck(fromIndex, toIndex, nums.length);
        int sum = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            sum += nums[i];
        }
        return sum;
    }
    
}
