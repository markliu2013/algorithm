package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CollectionUtil {
    
    private CollectionUtil() { }
    
    // 组合结果的最大值。
    private static final int COMBINATION_MAX_SIZE = 5200300;
    
    // combine支持的最大集合
    private static final int MAX_SIZE_COMBINE = 500;
    
    // subsets支持的最大集合
    private static final int MAX_SIZE_SUBSETS = 20;

    /**
     * <p>list的所有组合，list中不能有重复元素。如果无法确认是否有重复元素请使用{@link #subsetsRemoveDup(List) subsetsRemoveDup}方法。</p>
     * <p>返回的list按size从小到大排序，以便提前找到最优解。</p>
     * @param list
     * @return
     */
    public static <T> List<List<T>> subsets(List<T> list) {
        if (list.size() > MAX_SIZE_SUBSETS) {
            throw new IllegalArgumentException("the size of list is larger than " + MAX_SIZE_SUBSETS);
        }
        List<List<T>> solutionList = new ArrayList<>();
        solutionList.add(new ArrayList<>());
        for (int i = 1; i <= list.size(); i++) {
            solutionList.addAll(combine(list, i));
        }
        return solutionList;
    }
    
    /**
     * <p>list的所有组合，list中可以有重复元素。如果已确认无重复元素请使用{@link #subsets(List) subsets}方法，效率更高。</p>
     * <p>返回的list按size从小到大排序，以便提前找到最优解。</p>
     * @param list
     * @return
     */
    public static <T> List<List<T>> subsetsRemoveDup(List<T> list) {
        if (list.size() > MAX_SIZE_SUBSETS) {
            throw new IllegalArgumentException("the size of list is larger than " + MAX_SIZE_SUBSETS);
        }
        List<List<T>> solutionList = new ArrayList<>();
        solutionList.add(new ArrayList<>());
        for (int i = 1; i <= list.size(); i++) {
            solutionList.addAll(combineRemoveDup(list, i));
        }
        return solutionList;
    }
    
    public static void combineRangeCheck(int k, int size) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }
        if (k > size) {
            throw new IllegalArgumentException("k is larger than the size of list");
        }
        // 会出现递归栈溢出 
        if (size > MAX_SIZE_COMBINE) {
            throw new IllegalArgumentException("the size of list is larger than " + MAX_SIZE_COMBINE);
        }
        // 结果超出范围，避免程序超时。
        if (MathUtil.combine(size, k).intValue() > COMBINATION_MAX_SIZE) {
            throw new IllegalArgumentException("the combination result is larger than " + COMBINATION_MAX_SIZE);
        }
    }
    
    /**
     * 找C(n,k), 指定数目k的组合。list中不能有重复元素。如果无法确认是否有重复元素请使用{@link #combineRemoveDup(List, int) combineRemoveDup}方法。
     * @param list
     * @param k
     * @return
     */
    public static <T> List<List<T>> combine(List<T> list, int k) {
        combineRangeCheck(k, list.size());
        List<List<T>> solutionList = new ArrayList<>();
        if (k == 0) {
            solutionList.add(new ArrayList<>());
            return solutionList;
        }
        if (k == list.size()) {
            solutionList.add(new ArrayList<>(list));
            return solutionList;
        }
        T lastItem = list.get(list.size()-1);
        List<T> subList = list.subList(0, list.size()-1);
        List<List<T>> list1 = combine(subList, k);
        solutionList.addAll(list1);
        List<List<T>> list2 = combine(subList, k-1);
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).add(lastItem);
        }
        solutionList.addAll(list2);
        return solutionList;
    }
    
    /**
     * 全排列
     * @param list
     * @return
     */
    public static <T> List<List<T>> permutate(List<T> list) {
        List<List<T>> result = new ArrayList<>();
        if (list.size() == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        /*
        if (list.size() == 1) {
            List<T> list2 = new ArrayList<>();
            list2.add(list.get(0));
            result.add(list2);
            return result;
        }
        */
        T lastItem = list.get(list.size()-1);
        List<T> subList = list.subList(0, list.size()-1);
        List<List<T>> subResult = permutate(subList);
        for (List<T> list2 : subResult) {
            for (int i = 0; i < subList.size(); i++) {
                List<T> list3 = new ArrayList<>(list2);
                list3.add(i, lastItem);
                result.add(list3);
            }
            List<T> list3 = new ArrayList<>(list2);
            list3.add(lastItem);
            result.add(list3);
        }
        return result;
    }
    
    /**
     * 不考虑list的顺序，判断set中是包含某个list。
     * @param set
     * @param list
     * @return
     */
    private static <T> boolean contains(Set<List<T>> set, List<T> list) {
        if (set.contains(list)) {
            return true;
        } else {
            for (List<T> list2 : set) {
                if (isEqualCollection(list, list2)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * 找C(n,k), 指定数目k的组合。list中可以有重复元素。如果已确认无重复元素请使用{@link #combine(List, int) combine}方法，效率更高。
     * @param list
     * @param k
     * @return
     */
    public static <T> Set<List<T>> combineRemoveDup(List<T> list, int k) {
        combineRangeCheck(k, list.size());
        Set<List<T>> solutionList = new HashSet<>();
        if (k == 0) {
            solutionList.add(new ArrayList<>());
            return solutionList;
        }
        if (k == list.size()) {
            solutionList.add(new ArrayList<>(list));
            return solutionList;
        }
        T lastItem = list.get(list.size()-1);
        List<T> subList = list.subList(0, list.size()-1);
        Set<List<T>> set1 = combineRemoveDup(subList, k);
        solutionList.addAll(set1);
        Set<List<T>> set2 = combineRemoveDup(list.subList(0, list.size()-1), k-1);
        for (List<T> list3 : set2) {
            list3.add(lastItem);
        }
        for (List<T> list2 : set2) {
            if (!contains(solutionList, list2)) {
                solutionList.add(list2);
            }
        }
        return solutionList;
    }
    
    /**
     * a - b，a和b是两个集合，返回a-b，不影响a，b。
     * @param a
     * @param b
     */
    public static <T> Collection<T> subtract(Iterable<? extends T> a, Iterable<? extends T> b) {
        List<T> result = new ArrayList<>();
        List<T> listB = new ArrayList<>();
        for (T elementB : b) {
            listB.add(elementB);
        }
        for (T elementA : a) {
            boolean contains = false;
            Iterator<T> iteratorB = listB.iterator();
            while (iteratorB.hasNext()) {
                T elementB = iteratorB.next();
                if (elementB == null) {
                    contains = elementA == null;
                } else {
                    if (elementB.equals(elementA)) {
                        contains = true;
                    }
                }
                if (contains) {
                    iteratorB.remove();
                    break;
                }
            }
            if (!contains) {
                result.add(elementA);
            }
        }
        return result;
    }
    
    /**
     * remove b from a，a和b是两个集合，类似a-b直接在集合a上进行操作。
     * @param a
     * @param b
     */
    public static <T> void remove(Iterable<T> a, Iterable<T> b) {
        Iterator<T> iteratorB = b.iterator();
        while (iteratorB.hasNext()) {
            T objB = iteratorB.next();
            Iterator<T> iteratorA = a.iterator();
            while (iteratorA.hasNext()) {
                if (objB.equals(iteratorA.next())) {
                    iteratorA.remove();
                    break;
                }
            }
        }
    }
    
    /**
     * 使用指定的Comparator，删除a中大于e的元素。
     * 可以灵活指定删除条件。实现例如以下功能：
     * 
     * <ul>
     * <li>删除integer集合中大于某个integer的所有元素。</li>
     * <li>删除integer集合中小于某个integer的所有元素。</li>
     * <li>删除string集合中长度大于某个string的所有元素</li>
     * </ul>
     * 
     * @param a
     * @param e
     * @param c 决定删除条件的Comparator
     * @return {@code true} 至少有一个元素被删除了，{@code false} a中没有元素被删除
     */
    public static <T> boolean remove(Iterable<T> a, T e, Comparator<? super T> c) {
        boolean retVal = false;
        Iterator<T> iterator = a.iterator();
        while (iterator.hasNext()) {
            if (c.compare(iterator.next(), e) > 0) {
                iterator.remove();
                retVal = true;
            }
        }
        return retVal;
    }
    
    /**
     * 创建一个整数列表。
     * @param start inclusive
     * @param stop exclusive
     * @param steop 步长
     * @return 
     */
    public static List<Integer> newIntList(int start, int stop, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step == 0");
        }
        List<Integer> result = new ArrayList<>();
        if (step > 0) {
            for (int i = start; i < stop; i += step) {
                result.add(i);
            }
        } else {
            for (int i = start; i > stop; i += step) {
                result.add(i);
            }
        }
        return result;
    }
    
    /**
     * 创建一个整数列表。step默认1。
     * @param start
     * @param stop
     * @return
     */
    public static List<Integer> newIntList(int start, int stop) {
        return newIntList(start, stop, 1);
    }
    
    /**
     * 创建一个整数列表。start默认0，step默认1。
     * @param stop
     * @return
     */
    public static List<Integer> newIntList(int stop) {
        return newIntList(0, stop, 1);
    }
    /**
     * 判断a和b两个集合，不考虑顺序的情况下是否相等。
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqualCollection(Collection<?> a, Collection<?> b) {
     // https://stackoverflow.com/questions/13501142/java-arraylist-how-can-i-tell-if-two-lists-are-equal-order-not-mattering
        if (a == null && b == null){
            return true;
        }
        if((a == null && b != null) 
          || a != null && b == null
          || a.size() != b.size()) {
            return false;
        }
        if (a.containsAll(b) && b.containsAll(a)) {
            return subtract(a, b).size() == 0;
        } else {
            return false;
        }
    }
    
    /**
     * 求和
     * @param nums
     * @return
     */
    public static int sum(Iterable<Integer> nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }
    
    /**
     * 指定范围的求和。
     * @param nums
     * @param fromIndex inclusive
     * @param toIndex exclusive
     * @return
     */
    public static int sum(List<Integer> nums, int fromIndex, int toIndex) {
        Utilities.indexRangeCheck(fromIndex, toIndex, nums.size());
        int sum = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            sum += nums.get(i);
        }
        return sum;
    }
    
    /**
     * 判断两个集合中是否有相同元素。
     * @param a
     * @param b
     * @return {@code true} a和b至少存在一个相同的元素，{@code false} a和b不存在相同元素
     */
    public static <T> boolean hasDuplicates(Iterable<? extends T> a, Iterable<? extends T> b) {
        Set<T> set1 = new HashSet<>();
        for (T t : a) {
            set1.add(t);
        }
        if (set1.size() == 0) {
            return false;
        }
        for (T t : b) {
            if (set1.contains(t)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 是否递增（非严格）
     * @param list
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isIncreasing(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i-1)) <= 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] toArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    public static Map<Integer, Integer> countToMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            } else {
                map.put(nums[i], 1);
            }
        }
        return map;
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap, boolean order) {
        List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Entry<K,V>>() {
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        Map<K, V> sortedMap = new LinkedHashMap<K, V>();
        for (Entry<K, V> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    
}