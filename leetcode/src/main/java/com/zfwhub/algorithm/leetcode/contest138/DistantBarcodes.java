package com.zfwhub.algorithm.leetcode.contest138;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/distant-barcodes/
public class DistantBarcodes {
    
    // 回溯  https://leetcode.com/contest/weekly-contest-138/submissions/detail/231388828/
    public static int[] solution1(int[] barcodes) {
        List<Integer> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(result, solution, barcodes);
        return CollectionUtil.toArray(result);
    }
    
    // https://leetcode.com/problems/distant-barcodes/discuss/299371/C%2B%2B-with-picture
    public static int[] solution2(int[] barcodes) {
        if (barcodes.length == 1) {
            return barcodes;
        }
        // Count occurrences of each barcode using a hash map
        Map<Integer, Integer> map = CollectionUtil.countToMap(barcodes);
        // Use a set to sort barcodes by their number of occurrences
        map = CollectionUtil.sortByValue(map, false);
        List<Integer> list = new ArrayList<>();//按occurrence排序
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                list.add(key);
            }
        }
        // Starting from most frequent, fill even positions with barcodes
        int countIndex = 0;
        for (int i = 0; i < barcodes.length; i=i+2) {
            barcodes[i] = list.get(countIndex);
            countIndex++;
        }
        // Then fill odd positions with remaining barcodes
        for (int i = 1; i < barcodes.length; i=i+2) {
            barcodes[i] = list.get(countIndex);
            countIndex++;
        }
        return barcodes;
    }
    
    private static boolean dfs(List<Integer> result, List<Integer> solution, int[] barcodes) {
        if (isASolution(solution, barcodes)) {
            processSolution(result, solution, barcodes);
            return true;
        } else {
            for (int i = 0; i < barcodes.length; i++) {
                if (isValid(solution, barcodes, i)) {
                    makeMove(solution, i);
                    if (dfs(result, solution, barcodes)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
        }
        return false;
    }
    
    private static boolean isASolution(List<Integer> solution, int[] barcodes) {
        return solution.size() == barcodes.length;
    }
    
    private static void processSolution(List<Integer> result, List<Integer> solution, int[] barcodes) {
        for (int i = 0; i < solution.size(); i++) {
            result.add(barcodes[solution.get(i)]);
        }
    }
    
    private static boolean isValid(List<Integer> solution, int[] barcodes, int i) {
        if (solution.size() == 0) {
            return true;
        }
        if (solution.contains(i)) {
            return false;
        }
        return barcodes[solution.get(solution.size()-1)] != barcodes[i];
    }
    
    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    
    
    public static void main(String[] args) {
       // System.out.println(Arrays.toString(solution1(new int[] {1,1,1,2,2,2})));
        //System.out.println(Arrays.toString(solution1(new int[] {1,1,1,1,2,2,3,3})));
        System.out.println(Arrays.toString(solution2(new int[] {1,1,1,2,2,2})));
        System.out.println(Arrays.toString(solution2(new int[] {1,1,1,1,2,2,3,3})));
        System.out.println(Arrays.toString(solution2(new int[] {2,1,1})));
        System.out.println(Arrays.toString(solution2(new int[] {1,1,2})));
        System.out.println(Arrays.toString(solution2(new int[] {2,2,2,1,5})));
        System.out.println(Arrays.toString(solution2(new int[] {1})));
    }

}
