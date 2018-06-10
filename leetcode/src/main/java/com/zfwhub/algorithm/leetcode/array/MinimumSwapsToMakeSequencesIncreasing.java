package com.zfwhub.algorithm.leetcode.array;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    
    // TODO MinimumSwapsToMakeSequencesIncreasing wrong
    public static int solution(int[] A, int[] B) {
        int ans=0;
        for (int i=0;i<A.length-1;i++) {
            if (A[i+1]<=A[i] || B[i+1]<=B[i]) {
                ans++;
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        int[] A1 = new int[] {1,3,5,4};
        int[] A2 = new int[] {1,2,3,7};
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.solution(A1, A2));
        
    }

}
