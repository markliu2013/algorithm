package com.zfwhub.algorithm.leetcode.tree;

import java.util.List;

import com.zfwhub.algorithm.utils.CollectionUtil;
import com.zfwhub.algorithm.utils.TreeNode;
import com.zfwhub.algorithm.utils.TreeUtil;

// https://leetcode.com/problems/validate-binary-search-tree/
public class ValidateBinarySearchTree {
    
    // 中序遍历，然后判断是否是升序排列
    public static boolean solution1(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> list = TreeUtil.inOrderTraverse(root);
        return CollectionUtil.isIncreasing(list);
    }
    
    public static void main(String[] args) {
        TreeNode root = TreeUtil.createLevelOrder(new int[] {1,1});
        System.out.println(solution1(root));
    }

}
