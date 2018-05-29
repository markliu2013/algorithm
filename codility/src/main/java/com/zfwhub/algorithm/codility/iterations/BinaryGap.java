package com.zfwhub.algorithm.codility.iterations;

/**
 * A binary gap within a positive integer N is any maximal
 * sequence of consecutive zeros that is surrounded by ones
 * at both ends in the binary representation of N.
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class BinaryGap {

    /**
     * 1. The first number can't be 0
     * 2. two variables, one presents the whole max,
     *    the other presents current sub loop Max currentMax
     * 3. for loop, if gets 0, currentMax+1; if 1, max is currentMax or not change.
     * 4. currentMax is 0, then try next 0.
     */
    public int solution(int N) {
        String binaryString = Integer.toBinaryString(N);
        int max = 0;
        int currentMax = 0;
        for (int i = 1; i < binaryString.length(); i++) {
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
