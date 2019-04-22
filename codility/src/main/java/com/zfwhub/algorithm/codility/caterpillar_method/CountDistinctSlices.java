package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.*;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
public class CountDistinctSlices {
    
    public static int MAX = 1000000000;
    
    // Performance 0  https://app.codility.com/demo/results/trainingX6QC88-U5K/
    public static int solution1(int M, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                HashSet<Integer> set = new HashSet<Integer>();
                for (int k = i; k <= j; k++) {
                    if (set.contains(A[k])) {
                        break;
                    } else {
                        set.add(A[k]);
                    }
                }
                if (set.size() == (j - i) + 1) {
                    count++;
                    if (count > MAX) {
                        return MAX;
                    }
                }
            }
        }
        return count;
    }
    
    // Performance 40 https://app.codility.com/demo/results/trainingKDBJN3-W8H/
    public static int solution2(int M, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            HashSet<Integer> set = new HashSet<Integer>();
            set.add(A[i]);
            count++;
            if (count > MAX) {
                return MAX;
            }
            for (int j = i+1; j < A.length; j++) {
                if (set.contains(A[j])) { // 检测到重复的话，后面不用检测了。
                    break;
                } else {
                    set.add(A[j]);
                    count++;
                    if (count > MAX) {
                        return MAX;
                    }
                }
            }
        }
        return count;
    }
    

    // 回溯法
    // https://app.codility.com/demo/results/trainingPWDKST-3EC/
    public static int solution3(int M, int[] A) {
        List<Integer> solution = new ArrayList<>();
        return dfs(solution, A, 0);
    }
    
    private static int dfs(List<Integer> solution, int[] A, int count) {
        if (isASolution(solution, A)) {
            count++;
            if (count > MAX) {
                return MAX;
            }
        } else {
            // 总共两个点，每个点都一个位置一个位置的尝试
            for (int i = 0; i < A.length; i++) {
                if (isValid(solution, A, i)) {
                    makeMove(solution, i);
                    count = dfs(solution, A, count);
                    unMakeMove(solution);
                }
            }
        }
        return count;
    }
    
    private static boolean isASolution(List<Integer> solution, int[] A) {
        return solution.size() == 2;
    }
    
    private static boolean isValid(List<Integer> solution, int[] A, int n) {
        if (solution.size() == 0) {
            return true;
        }
        int start = solution.get(0);
        if (n < start) {
            return false;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = start; i <= n; i++) {
           if (set.contains(A[i])) {
               return false;
           } else {
               set.add(A[i]);
           }
        }
        return true;
    }
    
    private static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    // 动态规划，递归。https://app.codility.com/demo/results/training3CMMYT-KSP/
    public static int solution4(int M, int[] A) {
        DPStatus dpStatus = new DPStatus(M);
        solution4DP(A, dpStatus);
        System.out.println(dpStatus);
        return dpStatus.result;
    }
    
    private static void solution4DP(int[] A, DPStatus dpStatus) {
        if (A.length == 1) {
            dpStatus.array[A[0]] = 0;
            dpStatus.index = 0;
            dpStatus.result = 1;
            return;
        }
        if (dpStatus.result >= MAX) {
            dpStatus.result = MAX;
            return;
        }
        int lastNum = A[A.length-1];
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        solution4DP(subA, dpStatus);
        if (dpStatus.array[lastNum] != -1) {
            dpStatus.index = Math.max(dpStatus.index, dpStatus.array[lastNum]+1);
        }
        dpStatus.array[lastNum] = A.length - 1;
        dpStatus.result += (A.length) - dpStatus.index;
    }
    
    // 动态规划，递推。内存吃不消。https://app.codility.com/demo/results/training962W6Q-8DV/  Performance 100
    public static int solution5(int M, int[] A) {
        DPStatus[] dp = new DPStatus[A.length];
        DPStatus firstDpStatus = new DPStatus(M);
        firstDpStatus.array[A[0]] = 0;
        firstDpStatus.index = 0;
        firstDpStatus.result = 1;
        dp[0] = firstDpStatus;
        for (int i = 1; i < A.length; i++) {
            DPStatus preDpStatus = dp[i-1];
            DPStatus currentDpStatus = preDpStatus.clone();
            if (preDpStatus.array[A[i]] != -1) {
                currentDpStatus.index = Math.max(preDpStatus.index, preDpStatus.array[A[i]]+1);
            }
            currentDpStatus.array[A[i]] = i;
            currentDpStatus.result += (i+1) - currentDpStatus.index;
            if (currentDpStatus.result >= MAX) {
                return MAX;
            }
            dp[i] = currentDpStatus;
        }
        return dp[dp.length-1].result;
    }
    
    // 动态规划，递推。优化内存。
    public static int solution6(int M, int[] A) {
        DPStatus dpStatus = new DPStatus(M);
        dpStatus.array[A[0]] = 0;
        dpStatus.index = 0;
        dpStatus.result = 1;
        for (int i = 1; i < A.length; i++) {
            DPStatus preDpStatus = dpStatus.clone();
            if (preDpStatus.array[A[i]] != -1) {
                dpStatus.index = Math.max(preDpStatus.index, preDpStatus.array[A[i]]+1);
            }
            dpStatus.array[A[i]] = i;
            dpStatus.result += (i+1) - dpStatus.index;
            if (dpStatus.result >= MAX) {
                return MAX;
            }
        }
        return dpStatus.result;
    }
    
    private static class DPStatus implements Cloneable {
        
        public int[] array; // 数组的索引对应A中的数字，值对应数字的索引。
        public int index; // 从这个index开始，后面无重复的数字。
        public int result; // 最后的结果

        public DPStatus(int M) {
            int[] array = new int[M+1];
            for (int i = 0; i < array.length; i++) {
                array[i] = -1;
            }
            this.array = array;
        }
        
        @Override
        protected DPStatus clone() {
            try {
                DPStatus newDpStatus = (DPStatus) super.clone();
//                newDpStatus.array = this.array.clone();
                return newDpStatus;
            } catch (CloneNotSupportedException e) {
                throw new InternalError();
            }
        }

        @Override
        public String toString() {
            return "DPStatus [array=" + Arrays.toString(array) + ", index=" + index + ", result=" + result + "]";
        }
        
    }
    
    // https://github.com/Mickey0521/Codility/blob/master/CountDistinctSlices.java
    
    public static void main(String[] args) {
        int[] A = new int[300000000];
        for (int i = 0; i < A.length; i++) {
            A[i] = 1;
        }
        int M = 6;
//        System.out.println(solution4(M, A));
//        System.out.println(solution5(M, A));
        System.out.println(solution6(M, A));
    }

}
