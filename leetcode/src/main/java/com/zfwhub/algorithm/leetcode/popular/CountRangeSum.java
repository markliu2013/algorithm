package com.zfwhub.algorithm.leetcode.popular;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/count-of-range-sum/
public class CountRangeSum {
    
    // prefixSums 强攻
    public static int solution1(int[] nums, int lower, int upper) {
        long[] prefixSums = ArrayUtil.prefixSums(nums);
        int count = 0;
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long sum = prefixSums[j+1]-prefixSums[i];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // 分治法
    public static int solution2(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] >= lower && nums[0] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int leftResult = solution2(leftNums, lower, upper);
        int rightResult = solution2(rightNums, lower, upper);
        long crossCount = 0;
        long sumLeft = 0;
        for (int i = mid - 1; i >= 0; i--) {
            sumLeft += nums[i];
            long sumRight = 0;
            for (int j = mid; j < nums.length; j++) {
                sumRight+= nums[j];
                long sum = sumLeft + sumRight;
                if (sum >= lower && sum <= upper) {
                    crossCount++;
                }
            }
        }
        return leftResult+rightResult+(int)crossCount;
    }
    
    // TODO CountRangeSum https://blog.csdn.net/jmspan/article/details/51266931
    // https://www.cnblogs.com/haoweizh/p/10211849.html
    // 使用TreeMap来保存每个前缀和的计数。
    public static int solution3(int[] nums, int lower, int upper) {
        if (nums == null || nums.length==0) return 0;
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for(int i=1; i<nums.length; i++) sums[i] = sums[i-1] + nums[i];
        int total = 0;
        TreeMap<Long, Integer> treemap = new TreeMap<>();
        for(int i=0; i<nums.length; i++) {
            if (lower <= sums[i] && sums[i] <= upper) total ++;
            for(Integer count: treemap.subMap(sums[i]-upper, true, sums[i]-lower, true).values()) {
                total += count;
            }
            Integer count = treemap.get(sums[i]);
            if (count == null) count = 1; else count ++;
            treemap.put(sums[i], count);
        }
        System.out.println(treemap);
        return total;
    }
    
    
    public static void main(String[] args) {
        int[] nums = new int[] {-2,5,-1};
        int lower = -2; 
        int upper = 2;
//        int[] nums = new int[] {-2147483647,0,-2147483647,2147483647};
//        int lower = -564; 
//        int upper = 3864;
        System.out.println(solution3(nums, lower, upper));
    }
    
}
