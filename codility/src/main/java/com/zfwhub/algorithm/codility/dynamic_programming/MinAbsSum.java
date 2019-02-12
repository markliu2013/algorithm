package com.zfwhub.algorithm.codility.dynamic_programming;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
// http://phiphy618.blogspot.com/2013/05/codility-delta-2011-minabssum.html
// 其实是把数组分成两部分，两部分的和差值最小。
public class MinAbsSum {
    
    // 组合暴力解法
    public static int solution1(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        // 所有选择的组合，选的为1，不选的为-1。因为A中有重复元素，必须使用索引判断。
        List<List<Integer>> list = CollectionUtil.subsets(CollectionUtil.newIntList(A.length));
        for (List<Integer> list2 : list) {
            // 组合生成S数组
            int[] S = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                if (list2.contains(i+1)) {
                    S[i] = 1;
                } else {
                    S[i] = -1;
                }
            }
            // 计算 val(A, S) 
            int absSum = 0;
            for (int i = 0; i < A.length; i++) {
                absSum +=  A[i]*S[i];
            }
            absSum = Math.abs(absSum);
            
            result = Math.min(result, absSum);
        }
        return result;
    }
    
    // TODO MinAbsSum https://codility.com/media/train/solution-min-abs-sum.pdf

    public static void main(String[] args) {
        int[] A = new int[] { 1, 2, 3, 5, 5 };
        System.out.println(MinAbsSum.solution1(A));
    }

}
