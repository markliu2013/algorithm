package com.zfwhub.algorithm.leetcode.contest135;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

public class Triangulation {
    
    public static int solution1(int[] A) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            list.add(i);
        }
        List<List<Integer>> combinations = CollectionUtil.combine(list, 3);
        List<List<List<Integer>>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, combinations, A.length-2);
        int minProduct = Integer.MAX_VALUE;
        for (int i = 0; i < solutionList.size(); i++) {
            List<List<Integer>> currentSolution = solutionList.get(i);
            int currentProduct = 0;
            for (int j = 0; j < currentSolution.size(); j++) {
                List<Integer> triangle = currentSolution.get(j);
                currentProduct += A[triangle.get(0)] * A[triangle.get(1)] * A[triangle.get(2)];
            }
            minProduct = Math.min(minProduct, currentProduct);
        }
        /*
        for (int i = 0; i < solutionList.size(); i++) {
            List<List<Integer>> currentSolution = solutionList.get(i);
            int currentProduct = 0;
            for (int j = 0; j < currentSolution.size(); j++) {
                List<Integer> triangle = currentSolution.get(j);
                currentProduct += A[triangle.get(0)] * A[triangle.get(1)] * A[triangle.get(2)];
            }
            if (currentProduct == minProduct) {
                System.out.println(currentSolution);
            }
        }
        */
        return minProduct;
    }
    
    private static void dfs(List<List<List<Integer>>> solutionList, List<Integer> solution, List<List<Integer>> combinations, int n) {
        if (isASolution(solution, n)) {
            processSolution(solutionList, solution, combinations);
        } else {
            for (int i = 0; i < combinations.size(); i++) {
                if (isValid(solution, i, combinations)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, combinations, n);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }
    
    private static void processSolution(List<List<List<Integer>>> solutionList, List<Integer> solution, List<List<Integer>> combinations) {
        List<List<Integer>> solution2 = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            solution2.add(new ArrayList<>(combinations.get(solution.get(i))));
        }
        solutionList.add(solution2);
    }
    
    // 必须间隔一个。
    private static boolean isValid(List<Integer> solution, int i, List<List<Integer>> combinations) {
        if (solution.size() == 0) {
            return true;
        }
        if (solution.get(solution.size()-1) < i) {
            for (int j = 0; j < solution.size(); j++) {
                if (check(combinations.get(solution.get(j)), combinations.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    // 有重叠返回true
    private static boolean check(List<Integer> triangle1, List<Integer> triangle2) {
        for (int i = 0; i < 3; i++) {
            int[] a = new int[2];
            if (i == 2) {
                a[0] = triangle1.get(0);
                a[1] = triangle1.get(i);
            } else {
                a[0] = triangle1.get(i);
                a[1] = triangle1.get(i+1);
            }
            for (int j = 0; j < 3; j++) {
                int[] b = new int[2];
                if (j == 2) {
                    b[0] = triangle2.get(0);
                    b[1] = triangle2.get(j);
                } else {
                    b[0] = triangle2.get(j);
                    b[1] = triangle2.get(j+1);
                }
                // 判断a和b是否相交
                if ((b[0] > a[0] && b[0] < a[1]) && b[1] > a[1]) {
                    return true;
                } else if ((a[0] > b[0] && a[0] < b[1]) && a[1] > b[1]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static int solution2(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < i + d; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }
    
    public static void main(String[] args) {
//        List<Integer> triangle1 = new ArrayList<>();
//        triangle1.add(1);
//        triangle1.add(2);
//        triangle1.add(4);
//        List<Integer> triangle2 = new ArrayList<>();
//        triangle2.add(1);
//        triangle2.add(3);
//        triangle2.add(4);
//        System.out.println(check(triangle1, triangle2));
//        
        int[] A = new int[] {59,20,40,86,18,43,78,83,95,27};
        System.out.println(solution1(A));
        System.out.println(solution2(A));
    }
    
}
