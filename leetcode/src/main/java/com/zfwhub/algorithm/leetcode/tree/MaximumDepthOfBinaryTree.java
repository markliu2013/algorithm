package com.zfwhub.algorithm.leetcode.tree;

import com.zfwhub.algorithm.utils.TreeNode;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class MaximumDepthOfBinaryTree {
    
    public static int solution1(TreeNode root) {
        return BinaryTreeLevelOrderTraversal.solution1(root).size();
    }
    
}
