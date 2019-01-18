package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.Collections;
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
    public static <T> List<List<T>> subsets(List<T> list, int k) {
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
            
        }
        if (k == list.size()) {
            
        }
        
        return solutionList;
    }
    
    
    /**
     * a - b，a和b是两个集合，直接在集合a上进行操作。
     * @param a
     * @param b
     */
    public static <T> void subtract(Iterable<T> a, Iterable<T> b) {
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

}