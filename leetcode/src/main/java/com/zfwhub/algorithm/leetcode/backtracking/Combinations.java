package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/combinations/
 */
public class Combinations {

    // Backtracking Solution Java
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combine(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }

    public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if (k == 0) {
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        for (int i = start; i <= n; i++) {
            comb.add(i);
            combine(combs, comb, i + 1, n, k - 1);
            comb.remove(comb.size() - 1);
        }
    }
    
    // A short recursive Java solution based on C(n,k)=C(n-1,k-1)+C(n-1,k)
    public List<List<Integer>> combine2(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine2(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(this.combine2(n - 1, k));
        return result;
    }

}
