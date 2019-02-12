package com.zfwhub.algorithm.codility.dynamic_programming;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
public class MinAbsSum {
    
    // 找出所有可能的组合
    public static int solution1(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        // 所有选择的组合，选的为1，不选的为-1。因为A中有重复元素，必须使用索引判断。
        List<List<Integer>> list = CollectionUtil.subsets(CollectionUtil.newIntList(A.length));
        for (List<Integer> list2 : list) {
            int absSum = 0;
            for (int i = 0; i < A.length; i++) {
                if (list2.contains(i+1)) {
                    absSum += A[i];
                } else {
                    absSum -= A[i];
                }
            }
            absSum = Math.abs(absSum);
            result = Math.min(result, absSum);
        }
        return result;
    }
    
    public static int solution2(int[] a){
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        int sum = 0;
        for (int i=0;i<a.length;i++){
            sum += Math.abs(a[i]);
        }
        int[] indices = new int[a.length];
        indices[0] = 0;
        int half = sum/2;
        int localSum = Math.abs(a[0]);
        int minLocalSum = Integer.MAX_VALUE;
        int placeIndex = 1;
        for (int i=1;i<a.length;i++){
            if (localSum<half){
                if (Math.abs(2*minLocalSum-sum) > Math.abs(2*localSum - sum))
                    minLocalSum = localSum;
                localSum += Math.abs(a[i]);
                indices[placeIndex++] = i;
            }else{
                if (localSum == half)
                    return Math.abs(2*half - sum);

                if (Math.abs(2*minLocalSum-sum) > Math.abs(2*localSum - sum))
                    minLocalSum = localSum;
                if (placeIndex > 1) {
                    localSum -= Math.abs(a[indices[placeIndex--]]);
                    i = indices[placeIndex];
                }
            }
        }
        return (Math.abs(2*minLocalSum - sum));
    }
    
   

    public static void main(String[] args) {
        int[] A = new int[] { 1, 2, 2, 3, 5, 5 };
        System.out.println(MinAbsSum.solution1(A));
        System.out.println(MinAbsSum.solution2(A));
    }

}
