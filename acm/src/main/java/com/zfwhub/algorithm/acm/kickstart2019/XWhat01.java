package com.zfwhub.algorithm.acm.kickstart2019;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051061/0000000000161426
public class XWhat01 {
    public static void main(String[] args) {
//        test();
        Scanner sc = new Scanner(System.in);
        try {
            // T组测试用例，循环每一个测试用例。
            int T = sc.nextInt();
            for (int i = 0; i < T; i++) {
                // 每个测试用例下面的第一个数是数组长度N，
                int N = sc.nextInt();
                // 第二个数是修改的次数
                int Q = sc.nextInt();
                // 初始化数组A
                int[] A = new int[N];
                // 下面一排就是数组A
                for (int j = 0; j < N; j++) {
                    A[j] = sc.nextInt();
                }
                // 一共Q次修改
                for (int j = 0; j < Q; j++) {
                    // Q的第一个数是索引
                    int index = sc.nextInt();
                    // 第二个数是要改成什么值
                    int value = sc.nextInt();
                    // 对A进行修改，直接在A的基础上改。题目的意思，不要创建新数组。
                    A[index] = value;
                    // 要开始找修改之后A的largest xor-even subinterval
                    System.out.println(largestXOREvenSubinterval(A));
                }
            }
        } finally {
            sc.close();
        }
    }

    /*
     * 1. xor bitwise exclusive or 按位异或
     * 2. xor-sum 将数组元素依次xor的结果
     * 3. subinterval 子数组xor-sum的结果是一个整数，找到他的二进制数，二进制中1的个数是偶数
     * 4. A有2的N次方个子数组，这些子数组有subinterval
     */
    public static int largestXOREvenSubinterval(int[] A) {
        // 所有的子数组
        List<List<Integer>> subArraySet = CollectionUtil.subsets(ArrayUtil.toList(A));
        int result = 0;
        for (List<Integer> subArray : subArraySet) {
            // 每个子数组求xor-sum
            if (subArray.size() > 0) {
                int xorSum = getXORSum(subArray);
                if (Integer.bitCount(xorSum) % 2 == 0) {
                    result = Math.max(result, subArray.size());
                }
            }
        }
        return result;
    }

    public static int getXORSum(List<Integer> A) {
        if (A.size() == 0) {
            return 0;
        }
        int xorSum = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            xorSum ^= A.get(i);
        }
        return xorSum;
    }

    public static void test() {
        System.out.println(largestXOREvenSubinterval(new int[] { 10, 13, 3, 7 }));
        System.out.println(largestXOREvenSubinterval(new int[] { 32, 13, 3, 7 }));
        System.out.println(largestXOREvenSubinterval(new int[] { 32, 13, 22, 7 }));
        System.out.println(largestXOREvenSubinterval(new int[] { 14, 1, 15, 20, 26 }));
        //        System.out.println(getXORSum(Arrays.asList(new Integer[] {1, 15, 20, 26})));
        //        System.out.println(Integer.bitCount(0));
    }

}
