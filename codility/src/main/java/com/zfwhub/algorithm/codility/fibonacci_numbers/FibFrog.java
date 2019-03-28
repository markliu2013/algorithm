package com.zfwhub.algorithm.codility.fibonacci_numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://app.codility.com/programmers/lessons/13-fibonacci_numbers/fib_frog/
public class FibFrog {
    
    private final static int INFINITE = Integer.MAX_VALUE - 10; // 模拟无穷大

    // 动态规划，Performance 0
    public static int solution1(int[] A) {
        int target = A.length + 1;
        Set<Integer> fibs = getFibs(target);
        List<Integer> leaves = getLeaves(A);
        int[] dp = new int[leaves.size()];
        dp[0] = 0;
        for (int i = 1; i < leaves.size(); i++) {
            // 往左边找一个最小的
            int min = INFINITE;
            for (int j = 0; j < i; j++) {
                int gap = leaves.get(i) - leaves.get(j);
                if (fibs.contains(gap)) {
                    min = Math.min(min, dp[j]+1);
                }
            }
            dp[i] = min;
        }
        return dp[dp.length-1] >= INFINITE ? -1 : dp[dp.length-1];
    }
    
    // TODO FibFrog优化
    public static int solution2(int[] A) {
        return 0;
    }
    
    private static Set<Integer> getFibs(int target) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(1);
        int temp = result.get(result.size()-1) + result.get(result.size()-2);
        while (temp <= target) {
            result.add(temp);
            temp = result.get(result.size()-1) + result.get(result.size()-2);
        }
        return new HashSet<>(result);
    }
    
    private static List<Integer> getLeaves(int[] A) {
        List<Integer> leaves = new ArrayList<>();
        leaves.add(0);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                leaves.add(i+1);
            }
        }
        leaves.add(A.length+1);
        return leaves;
    }

    public static void main(String[] args) {
       System.out.println(solution1(new int[] { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 }));
    }
}
