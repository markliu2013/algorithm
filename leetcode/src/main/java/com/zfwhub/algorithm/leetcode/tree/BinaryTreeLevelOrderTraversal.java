package com.zfwhub.algorithm.leetcode.tree;

import java.util.*;

import com.zfwhub.algorithm.utils.TreeNode;
import com.zfwhub.algorithm.utils.TreeUtil;

// https://leetcode.com/problems/binary-tree-level-order-traversal/ 
public class BinaryTreeLevelOrderTraversal {
    
    public static List<List<Integer>> solution1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        while (currentLevel.size() > 0) {
            List<Integer> currentVals = new ArrayList<>();
            List<TreeNode> newLevel = new ArrayList<>();
            for (TreeNode node : currentLevel) {
                currentVals.add(node.val);
                if (node.left != null) {
                    newLevel.add(node.left);
                }
                if (node.right != null) {
                    newLevel.add(node.right);
                }
            }
            result.add(new ArrayList<>(currentVals));
            currentLevel = newLevel;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(TreeUtil.createLevelOrder(new int[] {1,2,3,4,5})));
    }
    
}
