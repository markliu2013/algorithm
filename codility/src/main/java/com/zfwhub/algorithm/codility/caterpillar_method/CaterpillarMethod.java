package com.zfwhub.algorithm.codility.caterpillar_method;

/**
 * https://codility.com/media/train/13-CaterpillarMethod.pdf
 * https://leetcode.com/articles/two-pointer-technique/
 */
public class CaterpillarMethod {

    public static boolean solution(int[] A, int s) {
        int front = 0;
        int total = 0;
        for (int i = 0; i < A.length; i++) {
            while (front < A.length && total + A[front] <= s) {
                total += A[front];
                front += 1;
            }
            if (total == s) {
                return true;
            }
            total -= A[i];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = new int[] { 6, 2, 7, 4, 1, 3, 6 };
        System.out.println(CaterpillarMethod.solution(A, 8));
    }

}
