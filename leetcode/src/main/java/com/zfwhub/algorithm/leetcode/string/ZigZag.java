package com.zfwhub.algorithm.leetcode.string;

// https://leetcode.com/problems/zigzag-conversion/description/
public class ZigZag {
    
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // TODO ZigZag save space
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
    // TODO ZigZag
    // https://blog.csdn.net/tonywearme/article/details/47719841
    public static String convert2(String s, int numRows) {
        return "";
    }

    public static void main(String[] args) {
        String s = "A";
        int numRows = 1;
        System.out.println(ZigZag.convert(s, numRows));
    }

}
