package com.zfwhub.algorithm.codility.iterations;
/**
 * 数字的二进制表示的字符串, 找一个连续的0最多的被1包围的子串。
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class BinaryGap {

    /**
     * 1. 第一位肯定不为0
     * 2. 定义两个变量, 一个记录总的max, 一个记录当前子串的currentMax
     * 一次for循环, 遇到0 currentMax+1 遇到1 max选择currentMax或max,
     * 然后currentMax置为0, 开始下一轮找0
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
