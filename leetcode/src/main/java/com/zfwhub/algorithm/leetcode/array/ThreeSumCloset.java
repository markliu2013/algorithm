package com.zfwhub.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumCloset {

    public int solution(int[] nums, int target) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int gap = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    if (gap < min) {
                        min = gap;
                        result = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return result;
    }

    public int solution2(int[] nums, int target) {
        // 优化数组，删除出现超过三次的元素
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Integer> list2 = new ArrayList<Integer>(); //nums 优化后的list, 删除超过三次的元素。
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list2.add(entry.getKey());
            if (entry.getValue() > 1) {
                list2.add(entry.getKey());
            }
        }
        Collections.sort(list2);

        return 0;
    }

}
