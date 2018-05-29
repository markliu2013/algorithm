package com.zfwhub.algorithm.codility.counting_elements;

import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
 */
public class MaxCounters {

    /**
     * straightforward
     */
    public int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > N) {
                //max counter very often
                for (int j = 0; j < result.length; j++) {
                    result[j] = max;
                }
            } else {
                result[A[i] - 1] = result[A[i] - 1] + 1;
                max = Math.max(max, result[A[i] - 1]);
            }
        }
        return result;
    }

    /**
     * remember the max number, then max operation at last.
     */
    public int[] solution2(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;// current max, not the number your MaxCounters at last 
        int max2 = 0;//the number your MaxCounters at last 
        boolean flag = false;//flag, if there is MaxCounters operation need at last. 
        for (int i = 0; i < A.length; i++) {
            if (A[i] > N) {
                flag = true;
                max2 = max;
            } else {
                if (flag) {//before your operation, you need check if need MaxCounters
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
        int[] A = new int[] { 3, 4, 4, 6, 1, 4, 4 };
        int[] result = mc.solution(N, A);
        System.out.println(Arrays.toString(result));
    }
}
