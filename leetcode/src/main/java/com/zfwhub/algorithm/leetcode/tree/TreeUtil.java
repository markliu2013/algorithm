package com.zfwhub.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    
    /**
     * https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
     * insert nodes in level order 
     */
    public static TreeNode insertLevelOrder(int[] arr) {
        
        return null;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    public static TreeNode cloneTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = mirrorTree(root.right);
        newNode.right = mirrorTree(root.left);
        return newNode;
    }

    public static boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTree = TreeUtil.mirrorTree(root);
        return TreeUtil.isSameTree(root, mirrorTree);
    }

    public static List<Integer> preOrderTraverse(TreeNode t) {
        List<Integer> list = new ArrayList<>();
        if (t != null) {
            list.add(t.val);
            preOrderTraverse(t.left);
            preOrderTraverse(t.right);
        }
        return list;
    }
    
    public static void main(String[] args) {
        
    }

}
