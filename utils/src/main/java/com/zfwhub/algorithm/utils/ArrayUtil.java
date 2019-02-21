package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    
    private ArrayUtil() { }
    
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
     * 创建一个整数数组。
     * @param start inclusive
     * @param stop exclusive
     * @param steop 步长
     * @return 
     */
    public static int[] newIntArray(int start, int stop, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step == 0");
        }
        if ((step > 0 && start >= stop) || (step < 0 && start <= stop)) {
            return new int[] {};
        }
        int[]result = new int[(int) Math.ceil(Math.abs((double)(stop-start)/step))];
        if (step > 0) {
            int index = 0;
            for (int i = start; i < stop; i += step) {
                result[index] = i;
                index++;
            }
        } else {
            int index = 0;
            for (int i = start; i > stop; i += step) {
                result[index] = i;
                index++;
            }
        }
        return result;
    }
    
    /**
     * 创建一个整数数组。step默认1。
     * @param start
     * @param stop
     * @return
     */
    public static int[] newIntArray(int start, int stop) {
        return newIntArray(start, stop, 1);
    }
    
    /**
     * 创建一个整数数组。start默认0，step默认1。
     * @param stop
     * @return
     */
    public static int[] newIntArray(int stop) {
        return newIntArray(0, stop, 1);
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
     * @param nums
     * @return
     */
    public static boolean isIncreasing(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i-1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 二维int数组转为为二维list
     * @param nums
     * @return
     */
    public static List<List<Integer>> twoDArrayToList(int[][] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] array : nums) {
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
     * 寻找数组区间中第一个不小于某个值的索引。<br/>
     * 因为区间是离散的，将返回值-1，即为小于某个值的索引。
     * <a href="https://en.cppreference.com/w/cpp/algorithm/lower_bound">https://en.cppreference.com/w/cpp/algorithm/lower_bound</a>
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return a中区间[fromIndex, toIndex)内满足x >= value的最小值x的位置。若这样的x不存在，返回toIndex。
     */
    public static int lowerBound(int[] a, int fromIndex, int toIndex, int key) {
        while (fromIndex < toIndex) { // 搜索区间[fromIndex, toIndex)不为空
            int mid = fromIndex + (toIndex - fromIndex) / 2; // 防溢出
            if (a[mid] < key) fromIndex = mid + 1;
            else toIndex = mid;
        }
        return fromIndex; // toIndex也行，因为[fromIndex, toIndex)为空的时候它们重合
    }
    
    /**
     * 
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    public static int upperBound(int[] a, int fromIndex, int toIndex, int key) {
        while (fromIndex < toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            if (a[mid] < key) fromIndex = mid + 1;
            else toIndex = mid;
        }
        return fromIndex;
    }
    
    /**
     * 寻找数组中第一个不小于某个值的索引。
     * @param a
     * @param key
     * @return
     */
    public static int lowerBound(int[] a, int key) {
        return lowerBound(a, 0, a.length, key);
    }
    
    /**
     * int数组求和
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
