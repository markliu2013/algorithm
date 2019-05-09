package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/triangle/
public class Triangle {
    
    public static int solution1(List<List<Integer>> triangle) {
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(triangle.get(0).get(0));
        dp.add(firstRow);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> preDp = dp.get(i-1);
            List<Integer> dpRow = new ArrayList<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dpRow.add(preDp.get(0)+triangle.get(i).get(j));
                } else  if (j == triangle.get(i).size()-1) {
                    dpRow.add(preDp.get(preDp.size()-1)+triangle.get(i).get(j));
                } else {
                    dpRow.add(Math.min(preDp.get(j-1), preDp.get(j)) + triangle.get(i).get(j));
                }
            }
            dp.add(dpRow);
        }
        return Collections.min(dp.get(dp.size()-1));
    }
    
    public static void main(String[] args) {
        List<List<Integer>> triangle = ArrayUtil.toList(new int[][] {{2},{3,4},{6,5,7},{4,1,8,3}});
        System.out.println(solution1(triangle));
    }
    
}
