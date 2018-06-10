package com.zfwhub.algorithm.leetcode.string;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * https://leetcode.com/contest/weekly-contest-88/problems/shifting-letters/
 */
public class ShiftingLetters {

    public static String solution(String S, int[] shifts) {
        long[] suffixSums = new long[shifts.length];
        suffixSums[shifts.length-1] = shifts[shifts.length-1];
        for (int i = shifts.length-2; i >= 0; i--) {
            suffixSums[i] = suffixSums[i+1] + shifts[i];
        }
        System.out.println(Arrays.toString(suffixSums));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < suffixSums.length; i++) {
            char c = increaseChar(S.charAt(i), suffixSums[i]);
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static char increaseChar(char c, long i) {
        long shiftTimes = i % 26;
        char result = c;
        for (int j = 0; j < shiftTimes; j++) {
            result = (char)(result + 1);
            if (result == '{') {
                result = 'a';
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        String S3 = "mkgfzkkuxownxvfvxasy";
        int[] shifts3 = new int[] {505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513};
        System.out.println(ShiftingLetters.solution(S3, shifts3));
    }

}
