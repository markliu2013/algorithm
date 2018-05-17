package com.zfwhub.algorithm.math.pac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * get Combination
 */
public class Combination {

    /**
     * assume we have arr [1,2,3], try to get all of combinations
     * C(3,0)+C(3,1)+C(3,2)+C(3,3)=1+3+3+1=8=pow(2,3)
     * try to look the array elements as turn off switch, so we can have pow(2,3)
     */
    public static List<List<Integer>> getAllCombination(int[] arr) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int count = new Double(Math.pow(2.0, arr.length)).intValue();
        for (int i = 0; i < count; i++) {
            List<Integer> list1 = new ArrayList<Integer>();
            String binaryString = String.format("%3s", Integer.toBinaryString(i)).replace(' ', '0');
            char[] chars = binaryString.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    list1.add(arr[j]);
                }
            }
            list.add(list1);
        }
        return list;
    }

    /**
     * get combinations of n elements from list, 
     * dynamic programming, pascal's triangle
     */
    public static List<List<Integer>> getCombination(List<Integer> list, int n) {
        if (list == null || list.size() <=0 || n > list.size()) {
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
                List<List<Integer>> list10_1 = preList.get(j-1);
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

    // TODO
    /**
     * dynamic nested for loops
     */
    public static List<List<Integer>> getCombination(int[] arr, int n) {
        return null;
    }

}
