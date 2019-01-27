package com.zfwhub.algorithm.leetcode.dp;
import java.util.*;
import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
public class MinimumSwapsIncreasing {
    
    // 回溯法暴力破解, 用bestSolution找最优解。
    public static int minSwap(int[] A, int[] B) {
        List<Boolean> solution = new ArrayList<>();
        List<Boolean> bestSolution = new ArrayList<>();
        dfs(solution, bestSolution, A, B);
        return countSolution(bestSolution);
    }
    // 动态规划
    public static int minSwap2(int[] A, int[] B) {
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
                if (minSwap+1 > (double)A.length/2) {
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
                if (minSwap+1 > (double)A.length/2) {
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
    
    // 
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
    
    private static class DPResult {
        
        public int lastSwapped = 0; // 最后一个是否交换了，0代表不交换，1代表交换，-1代表是否交换结果都一样。
        public int minSwap = Integer.MAX_VALUE;
        
        @Override
        public String toString() {
            return "DPResult [lastSwapped=" + lastSwapped + ", minSwap=" + minSwap + "]";
        }
        
    }

    private static void dfs(List<Boolean> solution, List<Boolean> bestSolution, int[] A, int[] B) {
        if (isASolution(solution, A, B)) {
            processSolution(solution, bestSolution);
        } else {
            // 对应每个位置是否交换
            for (int i = 0; i < 2; i++) {
                if (isValid(solution, bestSolution, i, A, B)) {
                    makeMove(solution, i);
                    dfs(solution, bestSolution, A, B);
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

    private static void processSolution(List<Boolean> solution, List<Boolean> bestSolution) {
        if (countSolution(bestSolution) > countSolution(solution)) {
            bestSolution.clear();
            bestSolution.addAll(solution);
        }
    }
    
    // 0 false, 1 true 交换不可能超过数组的一半
    private static boolean isValid(List<Boolean> solution, List<Boolean> bestSolution, int i, int[] A, int[] B) {
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
        int[] A = new int[] {2,1,6,7,8,13,15,11,18,13,20,24,17,28,22,23,36,37,39,34,43,38,48,41,46,48,49,50,56,55,59,60,62,64,66,75,69,70,71,74,87,78,95,97,81,99,85,101,90,91,93,95,107,109,101,111,106,114,115,117,118,115,121,122,123,124,125,126,134,131,133,136,142,149,151,152,145,156,158,150,162,159,161,165,169,170,169,174,172,176,177,181,183,192,186,188,189,196,198,200};
        int[] B = new int[] {0,4,10,11,12,9,10,16,12,19,15,16,25,20,33,34,27,29,32,40,35,45,40,50,51,52,53,55,52,58,58,61,62,66,71,68,78,81,83,84,75,91,79,80,98,83,100,89,102,103,105,106,96,98,110,105,113,109,110,111,112,120,116,118,126,130,131,133,129,137,138,140,137,138,140,142,154,147,149,159,152,163,164,163,166,168,171,170,175,176,177,181,186,184,193,194,195,190,195,200};
        System.out.println(MinimumSwapsIncreasing.minSwap(A, B));
//        System.out.println(MinimumSwapsIncreasing.minSwap3(A, B));
    }

}