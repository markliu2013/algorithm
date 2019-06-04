package com.zfwhub.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/unique-binary-search-trees/
public class UniqueBinarySearchTrees {
    
    // https://leetcode.com/submissions/detail/228734372/ 超时
    public static int solution1(int n) {
        return solution1DP(CollectionUtil.newIntList(1, n+1));
    }
    
    public static int solution2(int n) {
        return solution2DP(CollectionUtil.newIntList(1, n+1), new HashMap<>());
    }
    
    public static int solution1DP(List<Integer> nodes) {
        if (nodes.size() == 0) {
            return 0;
        }
        if (nodes.size() == 1) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            List<Integer> left = nodes.subList(0, i);
            List<Integer> right = nodes.subList(i+1, nodes.size());
            if (left.size() != 0 && right.size() != 0) {
                count += solution1DP(left)*solution1DP(right);
            } else {
                // 不可能两个同时为0
                if (left.size() == 0) {
                    count += solution1DP(right);
                }
                if (right.size() == 0) {
                    count += solution1DP(left);
                }
            }
        }
        return count;
    }
    
    public static int solution2DP(List<Integer> nodes, Map<List<Integer>, Integer> map) {
        if (nodes.size() == 0) {
            return 0;
        }
        if (nodes.size() == 1) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            List<Integer> left = nodes.subList(0, i);
            List<Integer> right = nodes.subList(i+1, nodes.size());
            Integer leftResult = 0;
            if (map.containsKey(left)) {
                leftResult = map.get(left);
            } else {
                leftResult = solution2DP(left, map);
                map.put(left, leftResult);
            }
            Integer rightResult = 0;
            if (map.containsKey(right)) {
                rightResult = map.get(right);
            } else {
                rightResult = solution2DP(right, map);
                map.put(right, rightResult);
            }
            if (left.size() != 0 && right.size() != 0) {
                count += leftResult * rightResult;
            } else {
                // 不可能两个同时为0
                if (left.size() == 0) {
                    count += rightResult;
                }
                if (right.size() == 0) {
                    count += leftResult;
                }
            }
        }
        return count;
    }
    
    // https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
    
    public static void main(String[] args) {
//        System.out.println(solution1(19));
        System.out.println(solution2(19));
    }
    
}
