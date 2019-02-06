package com.zfwhub.algorithm.codility.prefix_sums;

// https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
public class MinAvgTwoSlice {

    // straightforward
    public static int solution1(int[] A) {
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += A[k];
                }
                float avg = sum / (float) (j - i + 1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }

    // prefixsum is avg
    // https://app.codility.com/demo/results/trainingUXQSX8-U8S/
    public static int solution2(int[] A) {
        float[] prefixSums = new float[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = (prefixSums[i - 1] * (i) + A[i]) / (i + 1);
        }
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                float avg = (prefixSums[j] * (j + 1) - (i >= 1 ? prefixSums[i - 1] : 0) * (i)) / (float) (j - i + 1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }

    // prefixSums optimization
    public static int solution3(int[] A) {
        int[] prefixSums = new int[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + A[i];
        }
        float minAvg = Float.MAX_VALUE;
        int minAvgIndex = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                float avg = (prefixSums[j] - (i >= 1 ? prefixSums[i - 1] : 0)) / (float) (j - i + 1);
                if (minAvg > avg) {
                    minAvg = avg;
                    minAvgIndex = i;
                }
            }
        }
        return minAvgIndex;
    }

    // TODO
    // https://www.martinkysel.com/codility-minavgtwoslice-solution/
    public static int solution4(int[] A) {
        return 0;
    }

}
