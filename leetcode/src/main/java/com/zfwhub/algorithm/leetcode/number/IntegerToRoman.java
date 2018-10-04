package com.zfwhub.algorithm.leetcode.number;

/**
 * https://leetcode.com/problems/integer-to-roman/description/
 */
public class IntegerToRoman {

    public static String solution(int num) {
        String[][] c = {
            { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
            { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
            { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
            { "", "M", "MM", "MMM"}
        };
        StringBuilder roman = new StringBuilder();
        roman.append(c[3][num / 1000 % 10]);
        roman.append(c[2][num / 100 % 10]);
        roman.append(c[1][num / 10 % 10]);
        roman.append(c[0][num % 10]);
        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(IntegerToRoman.solution(900));
    }

}
