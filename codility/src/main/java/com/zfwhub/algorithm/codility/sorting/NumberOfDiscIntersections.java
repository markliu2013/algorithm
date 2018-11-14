package com.zfwhub.algorithm.codility.sorting;

/**
 * https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 */
public class NumberOfDiscIntersections {

    /**
     * brute force, get all combinations of two, then check.
     * Be careful, overflow
     */
    public static int solution(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int distance = j - i;//distance of this two points
                if (A[i] >= distance - A[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // TODO
    /**
     * sorting optimization 
     */
    public static int solution2(int[] A) {
        int count = 0;

        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOfDiscIntersections.solution(new int[] { 1, 5, 2, 1, 4, 0 }));
    }

}
