package com.zfwhub.algorithm.codility.prefix_sums;

/**
 * https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
 */
public class PassingCars {

    /**
     * count how many 1 before every 0
     */
    public int solution(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] == 0 && A[j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * prefixSum, count how many 1 before every 0
     */
    public int solution2(int[] A) {
        int[] prefixSum = new int[A.length];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                count += prefixSum[A.length - 1] - prefixSum[i];
                if (count > 1000000000) {
                    return -1;
                }
            }
        }
        return count;
    }

    /**
     * count how many 0 before every 1
     */
    public int solution3(int[] A) {
        int countOfZeros = 0, count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0)
                countOfZeros++;
            if (A[i] == 1)
                count += countOfZeros;
            if (count > 1000000000)
                return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        PassingCars pc = new PassingCars();
        System.out.println(pc.solution2(new int[] { 0, 1, 0, 1, 1 }));
    }
}
