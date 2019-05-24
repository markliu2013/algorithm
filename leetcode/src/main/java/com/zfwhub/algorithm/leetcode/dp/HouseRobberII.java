package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/house-robber-ii/
public class HouseRobberII {
    
    public static int solution1(int[] nums) {
        return solution1DP(nums.clone(), nums.clone());
    }
    public static int solution1DP(int[] nums, int[] orgiNums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int lastItem = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] subNums2 = null;
        if (nums.length == orgiNums.length) {
            subNums2 = Arrays.copyOfRange(nums, 1, nums.length-2);
        } else {
            subNums2 = Arrays.copyOfRange(nums, 0, nums.length-2);
        }
        return Math.max(solution1DP(subNums, orgiNums), solution1DP(subNums2, orgiNums)+lastItem);
    }
    
    public static int solution2(int[] nums) {
        return solution2DP(nums, nums.length, new HashMap<>());
    }
    
    public static int solution2DP(int[] nums, int oriSize, Map<DPMapKey, Integer> map) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int lastItem = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] subNums2 = null;
        if (nums.length == oriSize) {
            subNums2 = Arrays.copyOfRange(nums, 1, nums.length-2);
        } else {
            subNums2 = Arrays.copyOfRange(nums, 0, nums.length-2);
        }
        int result1 = 0;
        DPMapKey key1 = new DPMapKey(subNums);
        if (map.containsKey(key1)) {
            result1 = map.get(key1);
        } else {
            result1 = solution2DP(subNums, oriSize, map);
            map.put(key1, result1);
        }
        int result2 = 0;
        DPMapKey key2 = new DPMapKey(subNums2);
        if (map.containsKey(key2)) {
            result2 = map.get(key2);
        } else {
            result2 = solution2DP(subNums2, oriSize, map);
            map.put(key2, result2);
        }
        return Math.max(result1, result2+lastItem);
    }
    
    private static class DPMapKey {
        public int[] nums;

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

        public DPMapKey(int[] nums) {
            this.nums = nums;
        }
        
    }
    
    public static void main(String[] args) {
        int[] array = new int[] {104,209,137,52,158,67,213,86,141,110,151,127,238,147,169,138,240,185,246,225,147,203,83,83,131,227,54,78,165,180,214,151,111,161,233,147,124,143};
        System.out.println(solution2(array));
//        System.out.println(solution2(new int[] {2,3,2}));
//        System.out.println(solution2(new int[] {1,2,3,1}));
    }

}
