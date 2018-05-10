package com.zfwhub.algorithm.codility.prefix.sums;

import java.util.Arrays;

public class GenomicRangeQuery {

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

}
