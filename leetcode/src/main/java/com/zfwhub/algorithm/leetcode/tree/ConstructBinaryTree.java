package com.zfwhub.algorithm.leetcode.tree;

import com.zfwhub.algorithm.leetcode.utils.TreeNode;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class ConstructBinaryTree {

    // https://www.cnblogs.com/xinchrome/p/4905608.html
    public static TreeNode solution1(int[] preorder, int[] inorder) {
        return null;
    }
    
    public static void main(String[] args) {
        int[] preorder = new int[] {3, 9, 20, 15, 7};
        int[] inorder = new int[] {9, 3, 15, 20, 7};
        TreeNode root = solution1(preorder, inorder);
        System.out.println(BinaryTreeLevelOrderTraversal.solution1(root));
    }

}
