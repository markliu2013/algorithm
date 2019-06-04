package com.zfwhub.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfASortedArray {
    
    public static int[] solution1(int[] A) {
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                nums1.add(0, A[i]*A[i]);
            } else {
                nums2.add(A[i]*A[i]);
            }
        }
        return ArrayUtil.mergeTwoSortedArray(CollectionUtil.toArray(nums1), CollectionUtil.toArray(nums2));
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution1(new int[] {-4,-1,0,3,10})));
    }

}
