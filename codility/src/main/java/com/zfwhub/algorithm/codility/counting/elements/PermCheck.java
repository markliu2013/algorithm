package com.zfwhub.algorithm.codility.counting.elements;

import java.util.HashSet;
/**
 * 判断一个数组是不是permutation, permutation是包含1到n的自然数, 且只有一次。
 * https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
 */
public class PermCheck {

    /**
     * 利用HashSet, 将数组全部添加进set。
     * 然后从1循环到n, 只要没找到就返回0, 循环结束返回1
     */
    public int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        for (int i = 1; i <= A.length; i++) {
            if (!set.contains(i)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * n是数组的长度
     * 如果数组中存在数字超出1到n范围, 则返回0。
     * 如果数组中所有的数字都在1和n之间,
     * 则1到n之间所有的数字都只能刚好出现一次。
     * 定义一个长度n的boolean的数组, 初始化都是false,
     * 索引对应数组中数字, 循环数组, 按索引置为true,
     * 如果遇到两次置为true的操作,返回0。 循环结束返回1。
     */
    public int solution2(int[] A) {
        boolean[] seen = new boolean[A.length + 1];//+1可以避免seen[A[i]-1]
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 1 || A[i] > A.length)
                return 0;
            if (seen[A[i]] == true)
                return 0;
            else
                seen[A[i]] = true;
        }
        return 1;
    }

    public static void main(String[] args) {
        PermCheck pc = new PermCheck();
        int result = pc.solution2(new int[] { 4, 1, 3, 2 });
        System.out.println(result);
    }
}
