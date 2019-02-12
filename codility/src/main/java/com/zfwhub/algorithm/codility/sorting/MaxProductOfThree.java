package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;

// https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
// https://leetcode.com/problems/maximum-product-of-three-numbers/
public class MaxProductOfThree {

    // brute force, count every combination.
    public static int solution1(int[] A) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    maxProduct = Math.max(maxProduct, A[i] * A[j] * A[k]);
                }
            }
        }
        return maxProduct;
    }

    // 排序之后，分情况讨论。
    public static int solution2(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        Arrays.sort(A);
        if (A[1] >= 0 || A[A.length - 1] <= 0) {//少于两个负数或没有正数, 则肯定是最后面三个数。
            return A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
        }
        if (A[A.length - 3] <= 0) {//少于两个正数
            return A[0] * A[1] * A[A.length - 1];
        }
        return Math.max(A[0] * A[1] * A[A.length - 1], A[A.length - 1] * A[A.length - 2] * A[A.length - 3]);
    }

    // solution2 化简
    public static int solution3(int[] A) {
        Arrays.sort(A);
        return Math.max(A[0] * A[1] * A[A.length - 1], A[A.length - 1] * A[A.length - 2] * A[A.length - 3]);
    }

    // 不需要排序，只需要找到两个最小的数，三个最大的数即可。
    public static int solution4(int[] A) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: A) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    public static void main(String[] args) {
        System.out.println(MaxProductOfThree.solution3(new int[] { -2, -3, -4 }));
    }

}
