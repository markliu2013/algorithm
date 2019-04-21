package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
     * 将整型的集合转化为int数组。
     * @param c
     * @return
     */
    public static int[] newIntArray(Iterable<Integer> c) {
        int size = 0;
        if (c instanceof Collection) {
            size = ((Collection<Integer>) c).size();
        } else {
            Iterator<Integer> iterator = c.iterator();
            while(iterator.hasNext()) {
                iterator.next();
                size++;
            }
        }
        int[] arr = new int[size];
        int index = 0;
        for (int i : c) {
            arr[index] = i;
            index++;
        }
        return arr;
    }
    
    /**
     * int数组转list
     * @param array
     * @return
     */
    public static List<Integer> toList(int[] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
           result.add(Integer.valueOf(array[i]));
        }
        return result;
    }
    
    /**
     * int数组转set
     * @param array
     * @return
     */
    public static Set<Integer> toSet(int[] array) {
        Set<Integer> result = new HashSet<>();
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
     * 寻找数组区间中第一个不小于某个值的索引。<br/>
     * 给定数组a、区间[fromIndex, toIndex)和一个目标值key，返回区间内<b>第一个</b><i>不小于</i>（即<i>大于或等于</i>）<code>key</code>的元素的位置。若不存在，返回<code>toIndex</code>。
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
     * 寻找数组区间中第一个大于某个值的索引。<br/>
     * 给定数组a、区间[fromIndex, toIndex)和一个目标值key，返回区间内<b>第一个</b><i>大于</i><code>key</code>的元素的位置。若不存在，返回<code>toIndex</code>。
     * 因为区间是离散的，将返回值-1，即为小于或等于某个值的索引。
     * <a href="https://en.cppreference.com/w/cpp/algorithm/lower_bound">https://en.cppreference.com/w/cpp/algorithm/lower_bound</a>
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return a中区间[fromIndex, toIndex)内满足x >= value的最小值x的位置。若这样的x不存在，返回toIndex。
     */
    public static int upperBound(int[] a, int fromIndex, int toIndex, int key) {
        while (fromIndex < toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            if (!(key < a[mid])) fromIndex = mid + 1;
            else toIndex = mid;
        }
        return fromIndex;
        // 可以使用JDK中的方法
        // return Math.abs(Arrays.binarySearch(a, fromIndex, toIndex, key)+1);
    }
    
    /**
     * 寻找数组中第一个不小于某个值的索引。
     * @param a
     * @param key
     * @return 如果存在则返回索引值，不存在则返回数组的长度。
     */
    public static int lowerBound(int[] a, int key) {
        return lowerBound(a, 0, a.length, key);
    }
    
    /**
     * 寻找数组中第一个大于某个值的索引。
     * @param a
     * @param key
     * @return 如果存在则返回索引值，不存在则返回数组的长度。
     */
    public static int upperBound(int[] a, int key) {
        return upperBound(a, 0, a.length, key);
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
    
    /**
     * int数组的组合，如果需要去重，请先将a排序。
     * @param a
     * @param k
     * @return
     */
    public static Set<List<Integer>> combine(int[] a, int k) {
        CollectionUtil.combineRangeCheck(k, a.length);
        Set<List<Integer>> solutionList = new HashSet<>();
        if (k == 0) {
            solutionList.add(new ArrayList<>());
            return solutionList;
        }
        if (k == a.length) {
            solutionList.add(toList(a));
            return solutionList;
        }
        int lastItem = a[a.length-1];
        int[] subArray = Arrays.copyOfRange(a, 0, a.length-1);
        Set<List<Integer>> set1 = combine(subArray, k);
        solutionList.addAll(set1);
        Set<List<Integer>> set2 = combine(subArray, k-1);
        for (List<Integer> list2 : set2) {
            list2.add(lastItem);
        }
        solutionList.addAll(set2);
        return solutionList;
    }
    
}
