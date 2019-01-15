package com.zfwhub.algorithm.leetcode.popular;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/combinations/
public class Combinations {
    
    public static List<List<Integer>> combine(int n, int k) {
        return CollectionUtil.subsets(ArrayUtil.toList(ArrayUtil.newIntArray(n)), k);
    }

    // https://leetcode.com/problems/combinations/discuss/27015/3-ms-Java-Solution
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        result = combine(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n);
        }
        result.addAll(combine(n - 1, k));
        return result;
    }
    
    /**
     * get combinations of n elements from list, 
     * dynamic programming, pascal's triangle
     */
    public static List<List<Integer>> getCombination(List<Integer> list, int n) {
        if (list == null || list.size() <= 0 || n > list.size()) {
            throw new RuntimeException("illegal parameters");
        }
        // preList is 3 dimensionals list, represents a row's all combination list
        // fill first row
        List<List<List<Integer>>> preList = new LinkedList<List<List<Integer>>>();
        List<Integer> list1 = new LinkedList<Integer>();
        List<List<Integer>> list2 = new LinkedList<List<Integer>>();
        list2.add(list1);
        preList.add(list2);
        List<Integer> list3 = new LinkedList<Integer>();
        list3.add(list.get(0));
        List<List<Integer>> list4 = new LinkedList<List<Integer>>();
        list4.add(list3);
        preList.add(list4);
        // fill one by one row
        for (int i = 1; i < list.size(); i++) {
            List<List<List<Integer>>> currList = new LinkedList<List<List<Integer>>>();
            List<Integer> list5 = new LinkedList<Integer>();
            List<List<Integer>> list6 = new LinkedList<List<Integer>>();
            list6.add(list5);
            currList.add(list6);
            for (int j = 1; j < preList.size(); j++) {
                List<List<Integer>> list8 = preList.get(j);
                List<List<Integer>> list10_1 = preList.get(j - 1);
                // deep copy list
                // TODO simplify deep copy list
                List<List<Integer>> list10 = new LinkedList<List<Integer>>();
                for (List<Integer> list10_1_1 : list10_1) {
                    List<Integer> list10_1_2 = new LinkedList<Integer>();
                    for (Integer integer : list10_1_1) {
                        list10_1_2.add(integer);
                    }
                    list10.add(list10_1_2);
                }
                for (int k = 0; k < list10.size(); k++) {
                    List<Integer> list12 = list10.get(k);
                    list12.add(list.get(i));
                }
                List<List<Integer>> list11 = new LinkedList<List<Integer>>();
                list11.addAll(list8);
                list11.addAll(list10);
                currList.add(list11);
            }
            List<Integer> list7 = new LinkedList<Integer>();
            for (int j = 0; j <= i; j++) {
                list7.add(list.get(i));
            }
            List<List<Integer>> list8 = new LinkedList<List<Integer>>();
            list8.add(list7);
            currList.add(list8);
            preList = currList;
        }
        return preList.get(n);
    }

    /**
     * dynamic nested for loops
     * @see DynamicFor
     */
    static int count = 0;
    public static List<List<Integer>> getCombination2(List<Integer> list, int n) {
        if (n == 0) {
            System.out.println(list);
            count++;
            return null;
        }
        n--;
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list2 = new ArrayList<Integer>(list);
            list2.remove(i);
            getCombination2(list2, n);
        }
        return null;
    }
    
    /**
     * dynamic nested for loops
     * @see DynamicFor
     */
    public static List<List<Integer>> getCombination3(Integer[] arr, int n) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        getCombination3Helper(arr, 1, n, stack, list);
        return list;
    }
    
    private static void getCombination3Helper(Integer[] arr, int x, int n, LinkedList<Integer> stack, List<List<Integer>> list) {
        if (n == 0) {
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            ArrayList<Integer> tempStack = new ArrayList<Integer>(stack);
            for (int i = tempStack.size()-1; i >= 0; i--) {
                list2.add(arr[tempStack.get(i)-1]);
            }
            list.add(list2);
            return;
        }
        for (int i = x; i <= arr.length; i++) {
            n--;
            stack.push(i);
            getCombination3Helper(arr, i+1, n, stack, list);
            stack.pop();
            n++;
        }
    }
    
    /**
     * dynamic nested for loops
     * @see DynamicFor
     */
    public static List<List<Integer>> getCombination4(Integer[] arr, int n) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        getCombination4Helper(arr, 0, n, stack, list);
        return list;
    }
    // x 控制循环起始的i，n控制循环层数
    private static void getCombination4Helper(Integer[] arr, int x, int n, LinkedList<Integer> stack, List<List<Integer>> list) {
        if (n == 0) {
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            ArrayList<Integer> tempStack = new ArrayList<Integer>(stack);
            for (int i = tempStack.size()-1; i >= 0; i--) {
                list2.add(arr[tempStack.get(i)]);
            }
            list.add(list2);
            return;
        }
        for (int i = x; i < arr.length; i++) {
            n--;
            stack.push(i);
            getCombination4Helper(arr, i+1, n, stack, list);
            stack.pop();
            n++;
        }
    }
    
    public static void main(String[] args) {
        //System.out.println(Combination.getAllCombination(new int[] {2,3,4}));
        Integer[] arr = new Integer[] {1,2,3,4};
        System.out.println(Combinations.getCombination(Arrays.asList(arr), 2));
        System.out.println(Combinations.getCombination3(arr, 2));
        System.out.println(Combinations.getCombination4(arr, 2));
        System.out.println(Combinations.combine2(40, 39));
    }

}
