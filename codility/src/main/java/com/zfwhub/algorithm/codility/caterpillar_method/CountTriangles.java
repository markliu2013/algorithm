package com.zfwhub.algorithm.codility.caterpillar_method;

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

}
