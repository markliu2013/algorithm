package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;
import java.util.HashSet;

// https://app.codility.com/programmers/lessons/6-sorting/distinct/
public class Distinct {

    // HashSet， 全部100分
    public static int solution1(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        return set.size();
    }

    // 排序之后判断相邻的数字是否相等
    public static int solution2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return 1;
        }
        int count = 1;
        Arrays.sort(A);
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] != A[i + 1]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Distinct.solution2(new int[] { -2, 1, 1, 2, 3, 1 }));
    }
}
