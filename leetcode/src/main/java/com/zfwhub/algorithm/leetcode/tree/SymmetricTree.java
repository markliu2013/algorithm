package com.zfwhub.algorithm.leetcode.tree;

public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTree = TreeUtil.mirrorTree(root);
        return TreeUtil.isSameTree(root, mirrorTree);
    }
}
