package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/count_triangles/
 */
public class CountTriangles {

    /**
     * brute force
     */
    public static int solution(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if (A[i] + A[j] > A[k] && A[i] + A[k] > A[j] && A[j] + A[k] > A[i]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // TODO CountTriangles https://www.cnblogs.com/grandyang/p/7053730.html
    public static int solution2(int[] A) {
        Arrays.sort(A);
        
        return 0;
    }

    public static void main(String[] args) {
        int[] A = new int[] {10,2,5,1,8,12};
        System.out.println(CountTriangles.solution2(A));
    }
}
