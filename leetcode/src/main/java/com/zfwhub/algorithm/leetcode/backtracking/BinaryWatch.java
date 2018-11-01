package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

/**
 *  https://leetcode.com/problems/binary-watch/
 */
public class BinaryWatch {
    
    public static Integer[] arr = new Integer[] {60, 60*2, 60*4, 60*8, 1, 2, 4, 8, 16, 32};
    public static int maxMinutes = 11*60 + 59;
    
    // wrong, 不能同时选择4个分针，比如n取4，选择 4, 8, 16, 32
    public static List<String> solution(int num) {
        List<String> result = new ArrayList<>();
        List<List<Integer>> list = getCombination4(arr, num);
        List<Integer> minutes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list1 = list.get(i);
            int mins = 0;
            for (int j = 0; j < list1.size(); j++) {
                mins += list1.get(j);
            }
            if (mins <= maxMinutes) {
                minutes.add(mins);
            }
        }
        Collections.sort(minutes);
        for (int i = 0; i < minutes.size(); i++) {
            result.add(minToTime(minutes.get(i)));
        }
        return result;
    }
    
    
    private static String minToTime(int mins) {
        int hour = mins / 60;
        int min = mins % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(hour));
        sb.append(":");
        sb.append(String.format("%2s", min).replace(' ', '0'));
        return sb.toString();
    }
    
    public static List<List<Integer>> getCombination4(Integer[] arr, int n) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        getCombination4Helper(arr, 0, n, stack, list);
        return list;
    }
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
        System.out.println(solution(2));
    }
    
}
