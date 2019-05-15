package com.zfwhub.algorithm.leetcode.tree;

import com.zfwhub.algorithm.utils.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/
 */
public class SameTree {
    
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
    
}
