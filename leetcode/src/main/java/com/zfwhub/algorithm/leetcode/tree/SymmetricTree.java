package com.zfwhub.algorithm.leetcode.tree;

import com.zfwhub.algorithm.utils.TreeNode;
import com.zfwhub.algorithm.utils.TreeUtil;

public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTree = TreeUtil.mirrorTree(root);
        return TreeUtil.isSameTree(root, mirrorTree);
    }
}
