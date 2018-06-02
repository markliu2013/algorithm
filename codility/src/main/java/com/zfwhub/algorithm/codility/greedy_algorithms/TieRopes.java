package com.zfwhub.algorithm.codility.greedy_algorithms;

/**
 * https://app.codility.com/programmers/lessons/16-greedy_algorithms/tie_ropes/
 */
public class TieRopes {
    
    public static int solution(int K, int[] A) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum >= K) {
                count++;
                sum = 0;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        int K = 4;
        int[] A = new int[] {1,2,3,4,1,1,3};
        System.out.println(TieRopes.solution(K, A));
    }

}
