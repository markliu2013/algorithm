package com.zfwhub.algorithm.leetcode.tree;

import com.zfwhub.algorithm.leetcode.utils.TreeNode;
import com.zfwhub.algorithm.leetcode.utils.TreeUtil;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTree = TreeUtil.mirrorTree(root);
        return TreeUtil.isSameTree(root, mirrorTree);
    }
}
