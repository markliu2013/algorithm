package com.zfwhub.algorithm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtil {
    
    private CollectionUtil() { }

    // TODO Convert a generic list to an array
    // https://www.cnblogs.com/grandyang/p/4309345.html 解法一
    public static <T extends Comparable<? super T>> List<List<T>> subset(List<T> list) {
        List<List<T>> result = new ArrayList<>();
        Collections.sort(list);
        result.add(new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<T> solution = new ArrayList<>(result.get(j));
                solution.add(list.get(i));
                result.add(solution);
            }
        }
        return result;
    }
    
    public static <T extends Comparable<? super T>> List<List<T>> subset(T[] array) {
        return subset(Arrays.asList(array));
    }
    
    public static <T extends Comparable<? super T>> Set<List<T>> subsetsWithDup(List<T> list) {
        return new HashSet<>(subset(list));
    }
    
    public static <T extends Comparable<? super T>> Set<List<T>> subsetsWithDup(T[] array) {
        return new HashSet<>(subset(array));
    }

}