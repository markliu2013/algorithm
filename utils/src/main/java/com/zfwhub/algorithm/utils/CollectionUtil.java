package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {
    
    private CollectionUtil() { }

    /**
     * list的所有组合，list中不能有重复元素。请确认list中无重复元素，否则请使用subsetsWithDup。
     * @param list
     * @return
     */
    public static <T> List<List<T>> subsets(List<T> list) {
        List<List<T>> solutionList = new ArrayList<>();
        subsetsHelper(solutionList, new ArrayList<>(), list, 0);
        return solutionList;
    }
    
    // backtrack
    private static <T> void subsetsHelper(List<List<T>> solutionList, List<T> solution, List<T> list, int start) {
        solutionList.add(new ArrayList<>(solution));
        for (int i = start; i < list.size(); i++) {
            solution.add(list.get(i));
            subsetsHelper(solutionList, solution, list, i+1);
            solution.remove(solution.size()-1);
        }
    }
    
    /**
     * list的所有组合，list中可以有重复元素。当年不确定是否list有重复。
     * @param list
     * @return
     */
    public static <T extends Comparable<? super T>> List<List<T>> subsetsWithDup(List<T> list) {
        List<List<T>> solutionList = new ArrayList<>();
        Collections.sort(list); //必须加上排序，以方便去重。
        subsetsWithDupHelper(solutionList, new ArrayList<>(), list, 0);
        return solutionList;
    }
    
    private static <T extends Comparable<? super T>> void subsetsWithDupHelper(List<List<T>> solutionList, List<T> solution, List<T> list, int start) {
        solutionList.add(new ArrayList<>(solution));
        for (int i = start; i < list.size(); i++) {
            if(i > start && list.get(i) == list.get(i-1)) continue; // skip duplicates
            solution.add(list.get(i));
            subsetsWithDupHelper(solutionList, solution, list, i+1);
            solution.remove(solution.size()-1);
        }
    }
    
    /**
     * 找C(n,k), 指定数目k的组合。请确认list中无重复元素，否则请使用subsetsWithDup。
     * @param list
     * @param k
     * @return
     */
    public static <T> List<List<T>> combine(List<T> list, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
        if (k > list.size()) {
            throw new IllegalArgumentException("k is larger than the size of list");
        }
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
        List<List<T>> list2 = combine(list.subList(0, list.size()-1), k-1);
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).add(lastItem);
        }
        solutionList.addAll(list2);
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
     */
    public static <T> void remove(Iterable<T> a, T e, Comparator<? super T> c) {
        Iterator<T> iterator = a.iterator();
        while (iterator.hasNext()) {
            if (c.compare(iterator.next(), e) > 0) {
                iterator.remove();
            }
        }
    }
    
    /**
     * 初始化包含1到n的list。
     * @param n
     * @return
     */
    public static List<Integer> newIntList(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(i+1);
        }
        return result;
    }
    
    /**
     * 判断a和b两个集合，不考虑顺序的情况下是否相等。
     * @param a
     * @param b
     * @return
     */
    // https://stackoverflow.com/questions/13501142/java-arraylist-how-can-i-tell-if-two-lists-are-equal-order-not-mattering
    public static boolean isEqualCollection(Collection<?> a, Collection<?> b) {
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
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static int sum(List<Integer> nums, int beginIndex, int endIndex) {
        int sum = 0;
        for (int i = beginIndex; i < endIndex; i++) {
            sum += nums.get(i);
        }
        return sum;
    }

}