package com.zfwhub.algorithm.codility.prefix.sums;

public class MinAvgTwoSlice {

    /*
     * https://app.codility.com/demo/results/trainingX97SBY-RG5/
     */
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
    
    /*
     * https://app.codility.com/demo/results/trainingUXQSX8-U8S/
     * prefixsum存储的是平均值
     */
    public int solution2(int[] A) {
        float[] prefixSums = new float[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = (prefixSums[i-1]*(i) + A[i]) / (i+1);
        }
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                float avg = (prefixSums[j]*(j+1) - (i >= 1 ? prefixSums[i-1] : 0)*(i)) / (float) (j-i+1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }
    
    /*
     * 利用prefixsum优化
     * https://app.codility.com/demo/results/trainingMATP7R-UUT/
     */
    public int solution3(int[] A) {
        int[] prefixSums = new int[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = prefixSums[i-1] + A[i];
        }
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                float avg = (prefixSums[j] - (i >= 1 ? prefixSums[i-1] : 0)) / (float) (j-i+1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }
    
}
