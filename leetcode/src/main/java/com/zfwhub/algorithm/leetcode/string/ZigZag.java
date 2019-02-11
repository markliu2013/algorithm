package com.zfwhub.algorithm.leetcode.string;

// https://leetcode.com/problems/zigzag-conversion/description/
public class ZigZag {
    
    public static String solution1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        Character[][] charMatrix = new Character[s.length()][numRows];
        char[] chars = s.toCharArray();
        int x = 0;
        int y = 0;
        boolean direFlag = false; //true is down, false is up.
        for (int i = 0; i < chars.length; i++) {
            charMatrix[x][y] = chars[i];
            if (i % (numRows-1) == 0) {
                direFlag = !direFlag;
            }
            if (direFlag) {
                y++;
            } else {
                x++;
                y--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (charMatrix[j][i] != null) {
                    sb.append(charMatrix[j][i]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(ZigZag.solution1(s, numRows));
    }

}
