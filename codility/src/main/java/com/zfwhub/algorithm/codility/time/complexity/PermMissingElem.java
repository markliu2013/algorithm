package com.zfwhub.algorithm.codility.time.complexity;

import com.zfwhub.algorithm.utils.NumberUtil;

/**
 * 找连续自然数中少掉的那个数字。
 * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
 */
public class PermMissingElem {

    // 1到n自然数求和, 然后和当前数组的和, 相减得到的数字即可。
    public int solution(int[] A) {
        //1到n自然数求和公式
        int sum1 = NumberUtil.getSumFromOne(A.length);
        int sum2 = 0;//数组的和
        for (int i = 0; i < A.length; i++) {
            sum2 += A[i];
        }
        return sum1 - sum2;
    }

    public static void main(String[] args) {
        PermMissingElem pse = new PermMissingElem();
        System.out.println(pse.solution(new int[] { 2, 3, 1, 5 }));
    }

}
