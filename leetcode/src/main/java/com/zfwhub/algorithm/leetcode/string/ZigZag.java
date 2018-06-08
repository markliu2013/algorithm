package com.zfwhub.algorithm.leetcode.string;

import java.util.Arrays;
/**
 * 
 */
public class ZigZag {
    
    public static String convert(String s, int numRows) {
        int colsNum = (int)Math.floor(s.length() / (double)numRows);
        char[][] charMatrix = new char[s.length()][numRows];
        char[] chars = s.toCharArray();
        int x = 0;
        int y = 0;
        boolean direFlag = true; //true is down, false is up.
//        charMatrix[x][y] = chars[0];
        System.out.println(Arrays.deepToString(charMatrix));
        for (int i = 0; i < chars.length; i++) {
            charMatrix[x][y] = chars[i];
            if (direFlag) {
                y++;
            } else {
                x++;
                y--;
            }
            if (i % (numRows-1) == 0) {
                direFlag = !direFlag;
            }
            charMatrix[x][y] = chars[i];
        }
        System.out.println(Arrays.deepToString(charMatrix));
        return "";
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(ZigZag.convert(s, numRows));
    }

}
