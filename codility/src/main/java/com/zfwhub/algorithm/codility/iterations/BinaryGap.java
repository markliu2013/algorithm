package com.zfwhub.algorithm.codility.iterations;
/**
 * Find longest sequence of zeros in binary representation of an integer.
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class BinaryGap {

    public int solution(int N) {
        String binaryString = Integer.toBinaryString(N);
        int max = 0;
        int currentMax = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '0') {
                currentMax++;
            } else {
                max = Math.max(currentMax, max);
                currentMax = 0;
            }
        }
        return max;
    }

}
