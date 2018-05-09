package com.zfwhub.algorithm.codility.prefix.sums;

public class MinAvgTwoSlice {

    public int solution(int[] A) {
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += A[k];
                }
                float avg = sum / (float) (j-i+1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }

}
