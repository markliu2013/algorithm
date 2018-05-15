package com.zfwhub.algorithm.codility.prefix.sums;

import java.util.Arrays;

/**
 * 问题的本质是要找一个数组中，指定范围中的最小数字。
 * 此问题特殊化为数组中的数字只有1-4,4种可能，基于此我们有优化的解法。
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
     * 关键点：每一个索引前面有1,2,3,4的个数
     * prefixSum的变形用法，考验活学活用的时候到了。
     * prefixSum可以定义为二维数组，分别存储1,2,3,4各有多少个。
     * 最后检查子数组中1,2,3,4的个数。
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
            if (prefixSums[Q[i]+1][0] - prefixSums[P[i]][0] > 0) {
                result[i] = 1;
            } else if (prefixSums[Q[i]+1][1] - prefixSums[P[i]][1] > 0) {
                result[i] = 2;
            } else if (prefixSums[Q[i]+1][2] - prefixSums[P[i]][2] > 0) {
                result[i] = 3;
            } else if (prefixSums[Q[i]+1][3] - prefixSums[P[i]][3] > 0) {
                result[i] = 4;
            }
        }
        return result;
    }
    public int[] solution3(String S, int[] P, int[] Q) {
        //used jagged array to hold the prefix sums of each A, C and G genoms
        //we don't need to get prefix sums of T, you will see why.
        int[][] genoms = new int[3][S.length()+1];
        //if the char is found in the index i, then we set it to be 1 else they are 0
        //3 short values are needed for this reason
        short a, c, g;
        for (int i=0; i<S.length(); i++) {
            a = 0; c = 0; g = 0;
            if ('A' == (S.charAt(i))) {
                a=1;
            }
            if ('C' == (S.charAt(i))) {
                c=1;
            }
            if ('G' == (S.charAt(i))) {
                g=1;
            }
            //here we calculate prefix sums. To learn what's prefix sums look at here https://codility.com/media/train/3-PrefixSums.pdf
            genoms[0][i+1] = genoms[0][i] + a;
            genoms[1][i+1] = genoms[1][i] + c;
            genoms[2][i+1] = genoms[2][i] + g;
        }

        int[] result = new int[P.length];
        //here we go through the provided P[] and Q[] arrays as intervals
        for (int i=0; i<P.length; i++) {
            int fromIndex = P[i];
            //we need to add 1 to Q[i], 
            //because our genoms[0][0], genoms[1][0] and genoms[2][0]
            //have 0 values by default, look above genoms[0][i+1] = genoms[0][i] + a; 
            int toIndex = Q[i]+1;
            if (genoms[0][toIndex] - genoms[0][fromIndex] > 0) {
                result[i] = 1;
            } else if (genoms[1][toIndex] - genoms[1][fromIndex] > 0) {
                result[i] = 2;
            } else if (genoms[2][toIndex] - genoms[2][fromIndex] > 0) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }
        return result;
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
