package com.zfwhub.algorithm.codility.arrays;

import java.util.HashSet;

// Find the only one unpaired number in array
// https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
public class OddOccurrencesInArray {

    // use HashSet, set.contains is O(1)
public static int solution1(int[] A) {
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

    // exclusive OR, xor
    // http://blog.sina.com.cn/s/blog_13c6397540102x0c3.html
    // https://blog.csdn.net/xy913741894/article/details/52145043
    public static int solution2(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 9, 3, 9, 3, 9, 7, 9 };
        System.out.println(solution2(arr));
    }

}
