package com.zfwhub.algorithm.codility.arrays;

import java.util.HashSet;

/**
 * https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 */
public class OddOccurrencesInArray {

    /**
     * 利用HashSet优化
     */
    public int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (set.contains(A[i])) {
                set.remove(A[i]);
            } else {
                set.add(A[i]);
            }
        }
        return set.iterator().next();
    }

    /**
     * 利用亦或
     * http://blog.sina.com.cn/s/blog_13c6397540102x0c3.html
     * https://blog.csdn.net/xy913741894/article/details/52145043
     * @return
     */
    public int solution2(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }


    public static void main(String[] args) {
        OddOccurrencesInArray odd = new OddOccurrencesInArray();
        int[] arr = new int[]{9,3,9,3,9,7,9};
        System.out.println(odd.solution2(arr));
    }

}
