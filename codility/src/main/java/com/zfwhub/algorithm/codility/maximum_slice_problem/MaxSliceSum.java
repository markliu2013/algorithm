package com.zfwhub.algorithm.codility.maximum_slice_problem;

import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
 * exactly the same with MaximumSubarray LeetCode 
 */
public class MaxSliceSum {
    
    /**
     * dynamic programming, down to top 
     */
    public static int solution(int[] A) {
        int[] max1 = new int[A.length];//end with i's MaxSliceSum
        int[] max2 = new int[A.length];//from 0 to i's MaxSliceSum
        max1[0] = A[0];
        max2[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (max1[i-1] + A[i] > A[i]) {
                max1[i] = max1[i-1] + A[i];
            } else {
                max1[i] = A[i];
            }
            if (max1[i] > max2[i-1]) {
                max2[i] = max1[i];
            } else {
                max2[i] = max2[i-1];
            }
        }
        return max2[A.length-1];
    }
    
    // TODO sliding window, maybe.
    /**
     * 
     *  
     */
    public static int solution3(int[] A) {
        int[] prefixSums = new int[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = prefixSums[i-1] + A[i];
        }
        
        int maxSliceSum = A[0];
        int startIndex = 0;// max start index
        int endIndex = 0;//max end index
        int currentStartIndex = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= 0) {
                
            } else {
                if (prefixSums[i-1] < 0) {//遇到正数，前面的加起来小于0
                    if (A[i] > maxSliceSum) {//这个数很大，前面的都淘汰。
                        maxSliceSum = A[i];
                        startIndex = i;
                        endIndex = i;
                        currentStartIndex = i;
                    } else {//继续观察
                        currentStartIndex = i;
                    }
                }
                if (prefixSums[i] - prefixSums[endIndex] > 0) {
                    endIndex = i;
                    maxSliceSum += prefixSums[i] - prefixSums[endIndex];
                }
            }
            
        }
        return maxSliceSum;
    }
    public static int solution2(int[] A) {
        int[] prefixSums = new int[A.length];
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = prefixSums[i-1] + A[i];
        }
        int maxSliceSum = A[0];
        int startIndex = 0;// max start index
        int endIndex = 0;//max end index
        int currentStartIndex = 0;
        for (int i = 1; i < A.length; i++) {
            if (prefixSums[i] - prefixSums[endIndex] > 0) {
                maxSliceSum += prefixSums[i] - prefixSums[endIndex];
                endIndex = i;
            }
            
        }
        return maxSliceSum;
    }
    
    public static int solution4(int[] a) {
        // growing negative -4,-3,-2,-1
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        if (max <= 0) {
            return max;
        }
        
        int start=0,end=0,curr_max=0,prev_max=0,start_o=0,i;
        prev_max = a[0];
        int n = a.length;
        for(i=0; i<n; i++){
            curr_max += a[i];
            if(curr_max < 0){
                start = i+1;
                curr_max = 0;
            }
            else if(curr_max > prev_max){
                end = i;
                start_o = start;
                prev_max = curr_max;
            }

        }
        System.out.println(start_o + " " + end);
        int sum = 0;
        for (int j = start_o; j <= end; j++) {
            sum += a[j];
        }
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(MaxSliceSum.solution4(new int[] {-4,-3,-2,-1}));
    }

}
