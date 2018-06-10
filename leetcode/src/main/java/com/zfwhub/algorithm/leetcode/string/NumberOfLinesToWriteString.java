package com.zfwhub.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/number-of-lines-to-write-string/description/
 */
public class NumberOfLinesToWriteString {
    
    public static int[] solution(int[] widths, String S) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < alphabet.length; i++) {
            map.put(alphabet[i], widths[i]);
        }
        char[] chars = S.toCharArray();
        int lines = 1;
        int currentWidth = map.get(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (currentWidth + map.get(chars[i]) > 100) {
                currentWidth = map.get(chars[i]);
                lines++;
            } else {
                currentWidth += map.get(chars[i]);
            }
        }
        return new int[]{lines, currentWidth};
    }
    public static void main(String[] args) {
        int[] widths = new int[]{4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String S = "ab";
        int[] result = NumberOfLinesToWriteString.solution(widths, S);
        System.out.println(Arrays.toString(result));
    }

}
