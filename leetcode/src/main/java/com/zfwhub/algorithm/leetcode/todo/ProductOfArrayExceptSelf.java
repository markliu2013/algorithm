package com.zfwhub.algorithm.leetcode.todo;

import java.util.Arrays;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/product-of-array-except-self/
public class ProductOfArrayExceptSelf {
    
    // 强攻
    public static int[] solution1(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        return result;
    }
    
    // Accepted 有点动态规划的思想 537 ms    
    public static int[] solution2(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        int product = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = product;
            // 这个for循环需要更新前面的。
            for (int j = 0; j < i; j++) {
                result[j] = result[j] * nums[i];
            }
            product *= nums[i];
        }
        return result;
    }
    
    // 分治算法 Accepted 5 ms
    public static int[] solution3(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length == 1) {
            result[0] = 1;
            return result;   
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int[] leftResult = solution3(leftNums);
        int[] rightResult = solution3(rightNums);
        int leftProduct = product(nums, 0, mid);
        int rightProduct = product(nums, mid, nums.length);
        for (int i = 0; i < leftResult.length; i++) {
            result[i] = leftResult[i] * rightProduct;
        }        
        for (int i = 0; i < rightResult.length; i++) {
            result[i+leftResult.length] = rightResult[i] * leftProduct;
        }
        return result;
    }
    
    // prefixProducts, prefixSums的思想。
    public static int[] solution4(int[] nums) {
        int[] result = new int[nums.length];
        int[] prefixProductsFromLeft = new int[nums.length+1];
        prefixProductsFromLeft[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            prefixProductsFromLeft[i+1] = prefixProductsFromLeft[i] * nums[i];
        }
        int[] prefixProductsFromRight = new int[nums.length+1];
        prefixProductsFromRight[0] = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            prefixProductsFromRight[nums.length-1-i+1] = prefixProductsFromRight[nums.length-1-i] * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            // 挖掉i的积
            result[i] = prefixProductsFromLeft[i] * prefixProductsFromRight[nums.length-i-1];
        }
        return result;
    }
    
    public static int product(int[] nums, int fromIndex, int toIndex) {
        int product = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            product *= nums[i];
        }
        return product;
    }
    
    public static void main(String[] args) {
        int[] nums = ArrayUtil.newIntArray(3, 9);
//        System.out.println(Arrays.toString(solution1(nums)));
        System.out.println(Arrays.toString(solution3(nums)));
        System.out.println(Arrays.toString(solution4(nums)));
    }
}
