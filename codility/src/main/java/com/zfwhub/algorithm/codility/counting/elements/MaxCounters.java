package com.zfwhub.algorithm.codility.counting.elements;

import java.util.Arrays;

/**
 * 
 * https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
 */
public class MaxCounters {
    
    /**
     * 按照题意，straightforward
     */
    public int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > N) {
                //频繁进行了max counter操作
                for (int j = 0; j < result.length; j++) {
                    result[j] = max;
                }
            } else {
                result[A[i]-1] = result[A[i]-1] + 1;
                max = Math.max(max, result[A[i]-1]);
            }
        }
        return result;
    }
    
    /**
     * 先把最大数记录，最后一次for循环操作。
     */
    public int[] solution2(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;//当前数组的最大数max，不代表最后一步的max值。
        int max2 = 0;//最后一次max操作为这个数。
        boolean flag = false;//标识位，是否有max操作待执行。
        for (int i = 0; i < A.length; i++) {
            if (A[i] > N) {
                flag = true;
                max2 = max;
            } else {
                if (flag) {//操作之前，需要将其对标max2
                    result[A[i] - 1] = Math.max(max2, result[A[i] - 1]);
                }
                result[A[i] - 1] = result[A[i] - 1] + 1;
                max = Math.max(max, result[A[i] - 1]);
            }
        }
        if (flag) {
            for (int i = 0; i < result.length; i++) {
                if (result[i] < max2) {
                    result[i] = max2;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        MaxCounters mc = new MaxCounters();
        int N = 5;
        int[] A = new int[] {3, 4, 4, 6, 1, 4, 4};
        int[] result = mc.solution(N, A);
        System.out.println(Arrays.toString(result));
    }
}
