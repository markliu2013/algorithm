package com.zfwhub.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    
    /**
     * https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
     * create binary tree in level order
     * 完全二叉树, n的左子树2n, 右子树2n+1 
     */
    public static TreeNode createLevelOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        List<TreeNode> lastTreeNodes = new ArrayList<>();//满二叉树最后一层
        lastTreeNodes.add(root);
        List<TreeNode> currentTreeNodes = new ArrayList<>();//最后一层
        for (int i = 1; i < arr.length; i++) {
            int countUpNodes = lastTreeNodes.size() * 2 - 1; //满二叉树节点数
            int index = (i + 1) - countUpNodes;
            TreeNode node = new TreeNode(arr[i]);
            currentTreeNodes.add(node);
            if (index % 2 != 0) {
                lastTreeNodes.get((index+1) / 2 - 1).left = node;
            } else {
                lastTreeNodes.get(index / 2 - 1).right = node;
                if (lastTreeNodes.size() == index / 2) {
                    lastTreeNodes = currentTreeNodes;
                    currentTreeNodes = new ArrayList<>();
                }
            }
        }
        return root;
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

    public static void preOrderTraverse(TreeNode t) {
            if (t == null) {
                return;
            }
            System.out.print(t.val+">");
            preOrderTraverse(t.left);
            preOrderTraverse(t.right);
    }
    
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t4.left = t8;
        t4.right = t9;
        TreeNode node = createLevelOrder(new int[] {1,2,3,4,5,6,7,8,9});
        preOrderTraverse(t1);
        System.out.println();
        preOrderTraverse(node);
    }

}
