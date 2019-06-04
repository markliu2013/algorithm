package com.zfwhub.algorithm.templates.dp;

import java.util.Arrays;
import java.util.HashMap;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    
    // 动态规划，递归。
    public static int solution1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        DPStatus dpStatus = new DPStatus();
        solution1DP(nums, dpStatus);
        return Math.max(dpStatus.increasingCountNotLast, dpStatus.increasingCountWithLast);
    }
    
    // 动态规划，递归+备忘录。
    public static int solution2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        DPStatus dpStatus = new DPStatus();
        solution2DP(nums, dpStatus, new HashMap<>());
        return Math.max(dpStatus.increasingCountNotLast, dpStatus.increasingCountWithLast);
    }
    
    private static void solution1DP(int[] nums, DPStatus dpStatus) {
        if (nums.length == 1) {
            dpStatus.increasingCountNotLast = 0;
            dpStatus.increasingCountNotLastEndNumber = null;
            dpStatus.increasingCountWithLast = 1;
            return;
        }
        if (nums.length == 2) {
            dpStatus.increasingCountNotLast = 1;
            dpStatus.increasingCountNotLastEndNumber = nums[0];
            if (nums[0] < nums[1]) {
                dpStatus.increasingCountWithLast = 2;
            } else {
                dpStatus.increasingCountWithLast = 1;
            }
            return;
        }
        int lastNum = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        solution1DP(subNums, dpStatus);
        int subIncreasingCountNotLast = dpStatus.increasingCountNotLast;
        int subIncreasingCountNotLastEndNumber = dpStatus.increasingCountNotLastEndNumber;
        int subIncreasingCountWithLast = dpStatus.increasingCountWithLast;
        // 算increasingCountNotLast
        dpStatus.increasingCountNotLast = Math.max(subIncreasingCountNotLast, subIncreasingCountWithLast);
        // 算increasingCountNotLastEndNumber
        if (subIncreasingCountNotLast < subIncreasingCountWithLast) {
            dpStatus.increasingCountNotLastEndNumber = subNums[subNums.length-1];
        } else {
            dpStatus.increasingCountNotLastEndNumber = subIncreasingCountNotLastEndNumber;
        }
        // 算increasingCountWithLast，去数组找所有小于lastNum，选increasingCountWithLast最大的那个数。
        int maxIncreasingCountWithLast = 0;
        for (int i = 0; i < subNums.length; i++) {
            if (subNums[i] < lastNum) {
                DPStatus dpStatus2 = new DPStatus();
                solution1DP(Arrays.copyOfRange(subNums, 0, i+1), dpStatus2);
                maxIncreasingCountWithLast = Math.max(maxIncreasingCountWithLast, dpStatus2.increasingCountWithLast);
            }
        }
        dpStatus.increasingCountWithLast = maxIncreasingCountWithLast+1;
    }

    private static void solution2DP(int[] nums, DPStatus dpStatus, HashMap<DPMapKey, DPStatus> map) {
        if (map.containsKey(new DPMapKey(nums))) {
            dpStatus = map.get(new DPMapKey(nums)).clone();
            return;
        }
        if (nums.length == 1) {
            dpStatus.increasingCountNotLast = 0;
            dpStatus.increasingCountNotLastEndNumber = null;
            dpStatus.increasingCountWithLast = 1;
            map.put(new DPMapKey(nums), dpStatus.clone());
            return;
        }
        if (nums.length == 2) {
            dpStatus.increasingCountNotLast = 1;
            dpStatus.increasingCountNotLastEndNumber = nums[0];
            if (nums[0] < nums[1]) {
                dpStatus.increasingCountWithLast = 2;
            } else {
                dpStatus.increasingCountWithLast = 1;
            }
            map.put(new DPMapKey(nums), dpStatus.clone());
            return;
        }
        int lastNum = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        if (map.containsKey(new DPMapKey(subNums))) {
            dpStatus = map.get(new DPMapKey(subNums)).clone();
        } else {
            solution2DP(subNums, dpStatus, map);
            map.put(new DPMapKey(subNums), dpStatus.clone());
        }
        
        int subIncreasingCountNotLast = dpStatus.increasingCountNotLast;
        int subIncreasingCountNotLastEndNumber = dpStatus.increasingCountNotLastEndNumber;
        int subIncreasingCountWithLast = dpStatus.increasingCountWithLast;
        // 算increasingCountNotLast
        dpStatus.increasingCountNotLast = Math.max(subIncreasingCountNotLast, subIncreasingCountWithLast);
        // 算increasingCountNotLastEndNumber
        if (subIncreasingCountNotLast < subIncreasingCountWithLast) {
            dpStatus.increasingCountNotLastEndNumber = subNums[subNums.length-1];
        } else {
            dpStatus.increasingCountNotLastEndNumber = subIncreasingCountNotLastEndNumber;
        }
        // 算increasingCountWithLast，去数组找所有小于lastNum，选increasingCountWithLast最大的那个数。
        int maxIncreasingCountWithLast = 0;
        for (int i = 0; i < subNums.length; i++) {
            if (subNums[i] < lastNum) {
                DPStatus dpStatus2 = new DPStatus();
                int[] subSubNums = Arrays.copyOfRange(subNums, 0, i+1);
                if (map.containsKey(new DPMapKey(subSubNums))) {
                    dpStatus2 = map.get(new DPMapKey(subSubNums));
                } else {
                    solution2DP(subSubNums, dpStatus2, map);
                    map.put(new DPMapKey(subSubNums), dpStatus2.clone());
                }
                maxIncreasingCountWithLast = Math.max(maxIncreasingCountWithLast, dpStatus2.increasingCountWithLast);
            }
        }
        dpStatus.increasingCountWithLast = maxIncreasingCountWithLast+1;
        map.put(new DPMapKey(nums), dpStatus.clone());
    }
    
    private static class DPStatus implements Cloneable {
        
        public Integer increasingCountNotLast; //最后一个不选的情况
        public Integer increasingCountNotLastEndNumber; //最后一个不选，以哪个数字结尾的。
        public Integer increasingCountWithLast; //最后一个必须选的情况
        
        @Override
        protected DPStatus clone() {
            try {
                return (DPStatus) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new InternalError();
            }
        }
        
        @Override
        public String toString() {
            return "DPStatus [increasingCountNotLast=" + increasingCountNotLast + ", increasingCountNotLastEndNumber=" + increasingCountNotLastEndNumber + ", increasingCountWithLast=" + increasingCountWithLast + "]";
        }
    }
    
    private static class DPMapKey {
        
        public int[] nums;

        public DPMapKey(int[] nums) {
            this.nums = nums;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(nums);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DPMapKey other = (DPMapKey) obj;
            if (!Arrays.equals(nums, other.nums))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "DPMapKey [nums=" + Arrays.toString(nums) + "]";
        }
    }
    
    // https://www.zhihu.com/question/23995189/answer/613096905
    public static int solution3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return ArrayUtil.max(dp);
    }
    
    // TODO LIS https://www.jianshu.com/p/409952e9cfd7
    public static int solution4(int[] nums) {
        
        return 0;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {};
        System.out.println(solution1(nums));
        System.out.println(solution3(nums));
    }
    
}
