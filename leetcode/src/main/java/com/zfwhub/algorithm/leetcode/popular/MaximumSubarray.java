package com.zfwhub.algorithm.leetcode.popular;

import java.util.Arrays;

import com.zfwhub.algorithm.utils.ArrayUtil;

/*
 * https://leetcode.com/problems/maximum-subarray/
 * https://blog.csdn.net/liu2012huan/article/details/51296635
 * https://zhuanlan.zhihu.com/p/37570405
 * @link com.zfwhub.algorithm.codility.maximum_slice_problem.MaxSliceSum
 */
public class MaximumSubarray {

    // straightforward, brute force
    public static int solution1(int[] nums) {
        int maxSubArray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSubArray = Math.max(maxSubArray, sum);
            }
        }
        return maxSubArray;
    }

    // prefixSum
    public static int solution2(int[] nums) {
        int[] prefixSums = new int[nums.length];
        // get prefixSum
        prefixSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                max = Math.max(prefixSums[j] - (i >= 1 ? prefixSums[i - 1] : 0), max);
            }
        }
        return max;
    }

    // prefixSum's concept
    public static int solution3(int[] nums) {
        int maxSubArray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSubArray = Math.max(sum, maxSubArray);
            }
        }
        return maxSubArray;
    }

    // dynamic programming, low efficiency, deduce from start again
    public static int solution4(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int lastNum = nums[nums.length - 1];
        //don't choose the last number
        int[] subArray1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int max1 = solution4(subArray1);
        //choose the last number, deduce from start again
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i <= subArray1.length; i++) {
            int sum = 0;
            for (int j = i; j < subArray1.length; j++) {
                sum += nums[j];
            }
            sum += lastNum;
            max2 = Math.max(sum, max2);
        }
        return Math.max(max1, max2);
    }
    
    public static int solution4_1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int lastNum = nums[nums.length - 1];
        int[] subArray = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int max1 = solution4_1(subArray);
        int max2 = lastNum;
        int sum = max2;
        for (int i = nums.length-2; i >= 0; i--) {
            sum += nums[i];
            max2 = Math.max(sum, max2);
        }
        return Math.max(max1, max2);
    }
    
    public static int solution4_2(int[] nums) {
        // dp是最后的结果
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 计算dp[i]，根据dp[i-1]
            // 先算选择第i个数
            int max = nums[i];
            int sum = nums[i];
            for (int j = i-1; j >= 0; j--) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
            dp[i] = Math.max(dp[i-1], max);
        }
        return dp[dp.length-1];
    }
    
    // 最后找max，比前面的效率高
    public static int solution4_3(int[] nums) {
        // dp是必须选最后一个数
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
            dp[i] = max;
        }
        return ArrayUtil.max(dp);
    }
    
    public static int solution4_4(int[] nums) {
        // dp是必须选最后一个数
        int[] dp = new int[nums.length];
        // TODO solution4_4
        return ArrayUtil.max(dp);
    }

    // divide and conquer
    public static int solution5(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int maxLeft = solution5(leftNums);
        int maxRight = solution5(rightNums);
        //accross mid
        int midMaxLeft = Integer.MIN_VALUE;
        int midMaxRight = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid - 1; i >= 0; i--) {
            sum += nums[i];
            midMaxLeft = Math.max(midMaxLeft, sum);
        }
        sum = 0;
        for (int i = mid; i < nums.length; i++) {
            sum += nums[i];
            midMaxRight = Math.max(midMaxRight, sum);
        }
        int maxMid = midMaxLeft + midMaxRight;
        return Math.max(Math.max(maxLeft, maxRight), maxMid);
    }

    // dynamic programming - down to top
    public static int solution6(int[] nums) {
        int[] max = new int[nums.length];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        max[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(sum[i - 1] + nums[i], nums[i]);
            max[i] = Math.max(sum[i], max[i - 1]);
        }
        return max[nums.length - 1];
    }
    
    public static int solution6_1(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(sum[i - 1] + nums[i], nums[i]);
        }
        return ArrayUtil.max(sum);
    }

    // dynamic programming - down to top - optimize space
    public static int solution7(int[] nums) {
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /* end by nums[i]'s maximum */
            if (curSum < 0) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }
            /* to i's maximum */
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    // TODO MaximumSubarray 找到最大值，并且还要找出索引。
    // https://stackoverflow.com/questions/14180308/finding-the-start-and-end-index-for-a-max-sub-array/38023835#38023835
    public static int solution8(int[] a) {
        // growing negative -4,-3,-2,-1
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        if (max <= 0) {
            return max;
        }

        int start = 0, end = 0, curr_max = 0, prev_max = 0, start_o = 0, i;
        prev_max = a[0];
        int n = a.length;
        for (i = 0; i < n; i++) {
            curr_max += a[i];
            if (curr_max < 0) {
                start = i + 1;
                curr_max = 0;
            } else if (curr_max > prev_max) {
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

}
