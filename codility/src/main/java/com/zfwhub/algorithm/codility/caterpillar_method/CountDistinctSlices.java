package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.HashSet;
/**
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
 */
public class CountDistinctSlices {
    
    public static int MAX = 1000000000;
    
    /**
     * brute force
     */
    public static int solution(int M, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                HashSet<Integer> set = new HashSet<Integer>();
                for (int k = i; k <= j; k++) {
                    if (set.contains(A[k])) {
                        break;
                    } else {
                        set.add(A[k]);
                    }
                }
                if (set.size() == (j - i) + 1) {
                    count++;
                    if (count > MAX) {
                        return MAX;
                    }
                }
            }
        }
        return count;
    }
    
    // TODO CountDistinctSlices
    public static int solution2(int M, int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {3,4,5,5,2};
        int M = 6;
        System.out.println(CountDistinctSlices.solution(M, A));;
    }

}
