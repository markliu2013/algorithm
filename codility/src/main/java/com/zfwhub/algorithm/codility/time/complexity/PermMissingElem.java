package com.zfwhub.algorithm.codility.time.complexity;

import com.zfwhub.algorithm.utils.NumberUtil;

// https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
public class PermMissingElem {

    // 1到n自然数求和, 然后和当前数组的和, 相减得到的数字即可。
    public static int solution1(int[] A) {
        // 去掉数字之前的和
        long sum1 = NumberUtil.sum(1, A.length+1);
        long sum2 = 0;//数组的和
        for (int i = 0; i < A.length; i++) {
            sum2 += A[i];
        }
        return (int) (sum1 - sum2);
    }

    public static void main(String[] args) {
        System.out.println(solution1(new int[] { 2, 3, 1, 5 }));
    }

}
