package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/combinations/
 */
public class Combinations {

    // Backtracking Solution Java
    // https://blog.csdn.net/u010500263/article/details/18435495
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> combSet = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> comb = new ArrayList<Integer>();

        if (n < k) {
            return combSet;
        }
        helper(n, k, combSet, comb, 1);
        return combSet;
    }

    public void helper(int n, int k, ArrayList<ArrayList<Integer>> combSet, ArrayList<Integer> comb, int start) {
        // one possible combinition constructured
        if (comb.size() == k) {
            combSet.add(new ArrayList<Integer>(comb));
            return;
        } else {
            for (int i = start; i <= n; i++) {// try each possibility number in current position
                comb.add(i);
                helper(n, k, combSet, comb, i + 1); // after selecting number for current position, process next position
                comb.remove(comb.size() - 1); // clear the current position to try next possible number
            }
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

    public static void main(String[] args) {
        System.out.println(new Combinations().combine(4, 2));
        System.out.println(new Combinations().combine2(4, 2));
    }

}
