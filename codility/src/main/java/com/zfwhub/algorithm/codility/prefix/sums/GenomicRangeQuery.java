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

    // TODO
    /**
     * prefixSum的变形用法，考验活学活用的时候到了。
     * prefixSum可以定义为二维数组，分别存储1,2,3,4各有多少个。
     * 最后检查子数组中1,2,3,4的个数。
     */
    public int[] solution2(String S, int[] P, int[] Q) {
        int[][] prefixSums = new int[4][S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == 'A') {
                prefixSums[0][i + 1] = prefixSums[0][i] + 1;
            } else if (c == 'C') {
                prefixSums[1][i + 1] = prefixSums[1][i] + 1;
            } else if (c == 'G') {
                prefixSums[2][i + 1] = prefixSums[2][i] + 1;
            } else if (c == 'T') {
                prefixSums[3][i + 1] = prefixSums[3][i] + 1;
            } else {
                throw new RuntimeException("S is not right");

            }
        }
        System.out.println(Arrays.deepToString(prefixSums));
        return null;
    }
    
    public static void main(String[] args) {
        GenomicRangeQuery grq = new GenomicRangeQuery();
        int[] P = new int[] { 2, 5, 0 };
        int[] Q = new int[] { 4, 5, 6 };
        int[] result = grq.solution("CAGCCTA", P, Q);
        System.out.println(Arrays.toString(result));
    }

}
