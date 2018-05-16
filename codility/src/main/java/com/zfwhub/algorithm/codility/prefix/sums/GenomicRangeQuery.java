package com.zfwhub.algorithm.codility.prefix.sums;

import java.util.Arrays;

/**
 * Given range in an array, find the minimum number in the range.
 * In this problem there is only 4 possible numbers in the array,
 * base on this point, we can think optimization.
 * https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/
 */
public class GenomicRangeQuery {
    
    /**
     * straightforward
     */
    public int[] solution(String S, int[] P, int[] Q) {
        char[] chars = S.toCharArray();
        int[] factorsArr = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
            case 'A':
                factorsArr[i] = 1;
                break;
            case 'C':
                factorsArr[i] = 2;
                break;
            case 'G':
                factorsArr[i] = 3;
                break;
            case 'T':
                factorsArr[i] = 4;
                break;
            default:
                break;
            }
        }
        int[] result = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = P[i]; j <= Q[i]; j++) {
                min = Math.min(factorsArr[j], min);
            }
            result[i] = min;
        }
        return result;
    }

    /**
     * key point: how many [1, 2, 3, 4] you have before every index.
     * prefixSum ，It is time to apply your knowledge Creatively
     * prefixSum can be 2 dimensionals arry，store the count 1,2,3,4 respectively
     * at last, check the sub array's count of 1, 2, 3, 4
     */
    public int[] solution2(String S, int[] P, int[] Q) {
        int[][] prefixSums = new int[S.length()+1][4];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == 'A') {
                prefixSums[i+1][0] = prefixSums[i][0] + 1;
                prefixSums[i+1][1] = prefixSums[i][1];
                prefixSums[i+1][2] = prefixSums[i][2];
                prefixSums[i+1][3] = prefixSums[i][3];
            } else if (c == 'C') {
                prefixSums[i+1][1] = prefixSums[i][1] + 1;
                prefixSums[i+1][0] = prefixSums[i][0];
                prefixSums[i+1][2] = prefixSums[i][2];
                prefixSums[i+1][3] = prefixSums[i][3];
            } else if (c == 'G') {
                prefixSums[i+1][2] = prefixSums[i][2] + 1;
                prefixSums[i+1][0] = prefixSums[i][0];
                prefixSums[i+1][1] = prefixSums[i][1];
                prefixSums[i+1][3] = prefixSums[i][3];
            } else if (c == 'T') {
                prefixSums[i+1][3] = prefixSums[i][3] + 1;
                prefixSums[i+1][0] = prefixSums[i][0];
                prefixSums[i+1][1] = prefixSums[i][1];
                prefixSums[i+1][2] = prefixSums[i][2];
            } else {
                throw new RuntimeException("S is not right");
            }
        }
        int[] result = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            if (prefixSums[Q[i]+1][0] - prefixSums[P[i]][0] > 0) {// there is 1 in the range
                result[i] = 1;
            } else if (prefixSums[Q[i]+1][1] - prefixSums[P[i]][1] > 0) {// there is 2 in the range
                result[i] = 2;
            } else if (prefixSums[Q[i]+1][2] - prefixSums[P[i]][2] > 0) {// there is 3 in the range
                result[i] = 3;
            } else if (prefixSums[Q[i]+1][3] - prefixSums[P[i]][3] > 0) {// there is 4 in the range
                result[i] = 4;
            }
        }
        return result;
    }
    
    // TODO https://stackoverflow.com/questions/19552754/java-codility-training-genomic-range-query
    public int[] solution3(String S, int[] P, int[] Q) {
        return null;
    }
    
    public static void main(String[] args) {
        GenomicRangeQuery grq = new GenomicRangeQuery();
//        int[] P = new int[] { 2, 5, 0 };
//        int[] Q = new int[] { 4, 5, 6 };
//        int[] result = grq.solution2("CAGCCTA", P, Q);
        int[] P = new int[] { 0 };
        int[] Q = new int[] { 0 };
        int[] result = grq.solution2("A", P, Q);
        System.out.println(Arrays.toString(result));
    }

}
