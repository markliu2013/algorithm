package com.zfwhub.algorithm.leetcode.dp;
import java.util.*;
import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
public class MinimumSwapsIncreasing {
    
    // 回溯法暴力破解 
    public static int minSwap(int[] A, int[] B) {
        List<List<Boolean>> solutionList = new ArrayList<>();
        List<Boolean> solution = new ArrayList<>();
        dfs(solutionList, solution, A, B);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < solutionList.size(); i++) {
            min = Math.min(min, countSolution(solutionList.get(i)));
        }
        System.out.println(solutionList);
        return min;
    }
    
    // 回溯法暴力破解, 用bestSolution找最优解。已经大于当前最小，提前放弃。回溯法找最优解的套路。
    public static int minSwap2(int[] A, int[] B) {
        List<Boolean> solution = new ArrayList<>();
        List<Boolean> bestSolution = new ArrayList<>();
        dfs2(solution, bestSolution, A, B);
        return countSolution(bestSolution);
    }
    
    // 排列组合，从小到大的尝试。
    public static int minSwap3(int[] A, int[] B) {
        List<Integer> list = CollectionUtil.newIntList(A.length);
        for (int i = 0; i <= list.size()/2; i++) {
            List<List<Integer>> list2 = CollectionUtil.subsets(list, i);
            for (int j = 0; j < list2.size(); j++) {
                int[] newA = A.clone();
                int[] newB = B.clone();
                swap2(newA, newB, list2.get(j));
                if (ArrayUtil.isIncreasing(newA) && ArrayUtil.isIncreasing(newB)) {
                    return list2.get(j).size();
                }
            }
        }
        return -1;//无解
    }
    
    // 动态规划
    public static int minSwap4(int[] A, int[] B) {
        DPResult dpResult = new DPResult();
        minSwapDP(A, B, dpResult);
        System.out.println(dpResult);
        return dpResult.minSwap;
    }
    public static void minSwapDP(int[] A, int[] B, DPResult dpResult) {
        if (A.length == 1) {
            dpResult.lastSwapped = 0;
            dpResult.minSwap = 0;
            return;
        }
        int a1 = A[A.length-1]; // A的最后一个
        int a2 = A[A.length-2]; // A的倒数第二
        int b1 = B[B.length-1]; // B的最后一个
        int b2 = B[B.length-2]; // B的倒数第二
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        minSwapDP(subA, subB, dpResult);
        int minSwap = dpResult.minSwap;
        if (dpResult.lastSwapped == 1) {
            if (a1 <= b2 || b1 <= a2) {
                if (minSwap+1 > (double)A.length/2) { //这种情况不可能发生
                    dpResult.lastSwapped = 0;
                    dpResult.minSwap = A.length-(minSwap+1);
                } else if (minSwap+1 < (double)A.length/2) {
                    dpResult.minSwap = minSwap+1;
                } else {
                    dpResult.minSwap = minSwap+1;
                    dpResult.lastSwapped = -1;
                }
            }
        } else if (dpResult.lastSwapped == 0) {
            if (a1 <= a2 || b1 <= b2) {
                if (minSwap+1 > (double)A.length/2) { //这种情况不可能发生
                    dpResult.minSwap = A.length/2-(minSwap+1);
                } else if (minSwap+1 < (double)A.length/2) {
                    dpResult.minSwap = minSwap+1;
                    dpResult.lastSwapped = 1;
                    
                } else {
                    dpResult.minSwap = minSwap+1;
                    dpResult.lastSwapped = -1;
                }
            }
        } else {
            if ((a1 > a2 && b1 > b2) || (a1 > b2 && b1 > a2)) {
                dpResult.lastSwapped = 0;
            } else {
                dpResult.lastSwapped = 1;
            }
        }
    }
    
    private static class DPResult {
        
        public int lastSwapped = 0; // 最后一个是否交换了，0代表不交换，1代表交换，-1代表是否交换结果都一样。
        public int minSwap = Integer.MAX_VALUE;
        
        @Override
        public String toString() {
            return "DPResult [lastSwapped=" + lastSwapped + ", minSwap=" + minSwap + "]";
        }
        
    }
    private static void dfs(List<List<Boolean>> solutionList, List<Boolean> solution, int[] A, int[] B) {
        if (isASolution(solution, A, B)) {
            processSolution(solutionList, solution);
        } else {
            // 对应每个位置是否交换
            for (int i = 0; i < 2; i++) {
                if (isValid(solution, i, A, B)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, A, B);
                    unMakeMove(solution);
                }
            }
        }
    }
    private static void dfs2(List<Boolean> solution, List<Boolean> bestSolution, int[] A, int[] B) {
        if (isASolution(solution, A, B)) {
            processSolution2(solution, bestSolution);
        } else {
            // 对应每个位置是否交换
            for (int i = 0; i < 2; i++) {
                if (isValid2(solution, bestSolution, i, A, B)) {
                    makeMove(solution, i);
                    dfs2(solution, bestSolution, A, B);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    private static boolean isASolution(List<Boolean> solution, int[] A, int[] B) {
       if (solution.size() == A.length) {
            int[] newA = A.clone();
            int[] newB = B.clone();
            swap(newA, newB, solution);
            if (ArrayUtil.isIncreasing(newA) && ArrayUtil.isIncreasing(newB)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private static void processSolution(List<List<Boolean>> solutionList, List<Boolean> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    private static void processSolution2(List<Boolean> solution, List<Boolean> bestSolution) {
        if (countSolution(bestSolution) > countSolution(solution)) {
            bestSolution.clear();
            bestSolution.addAll(solution);
        }
    }
    // 0 false, 1 true 交换不可能超过数组的一半
    private static boolean isValid(List<Boolean> solution, int i, int[] A, int[] B) {
        // 为什么要加这个if，不然退出不了递归，请对比RestoreIpAddresses分析
        if (solution.size() > A.length) {
            return false;
        }
        if (i == 1 && countSolution(solution) + 1 > A.length / 2) {
            return false;
        } else {
            return true;
        }
    }
    private static boolean isValid2(List<Boolean> solution, List<Boolean> bestSolution, int i, int[] A, int[] B) {
        // 为什么要加这个if，不然退出不了递归，请对比RestoreIpAddresses分析
        if (solution.size() > A.length) {
            return false;
        }
        if (i == 1 && ((countSolution(solution) + 1 > A.length / 2) || (countSolution(solution) + 1 > countSolution(bestSolution)))) {
            return false;
        } else {
            return true;
        }
    }
    
    private static void makeMove(List<Boolean> solution, int n) {
        solution.add(n == 0 ? false : true);
    }
    
    private static void unMakeMove(List<Boolean> solution) {
        solution.remove(solution.size()-1);
    }
    
    private static int countSolution(List<Boolean> solution) {
        if (solution.size() == 0) {
            return Integer.MAX_VALUE;
        }
        int count = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                count++;
            }
        }
        return count;
    }
    
    private static void swap(int[] A, int[] B, List<Boolean> solution) {
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                int temp = A[i];
                A[i] = B[i];
                B[i] = temp;
            }
        }
    }
    
    private static void swap2(int[] A, int[] B, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i)-1;
            int temp = A[index];
            A[index] = B[index];
            B[index] = temp;
        }
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {0,7,8,10,10,11,12,13,19,18};
        int[] B = new int[] {4,4,5, 7,11,14,15,16,17,20};
        System.out.println(MinimumSwapsIncreasing.minSwap(A, B));
        System.out.println(MinimumSwapsIncreasing.minSwap4(A, B));
    }

}