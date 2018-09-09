package com.zfwhub.algorithm.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://blog.csdn.net/u012428012/article/details/79089915
 */
public class TreeUtil {
    
    /**
     * 1. 前序遍历
     * 递归
     * @param root 树根节点
     */
    public static void preorderTraversalRec(TreeNode root) {
       if (root == null) {
           return;
       }
       System.out.print(root.val + "->");
       preorderTraversalRec(root.left);
       preorderTraversalRec(root.right);
    }
    
    /**
     * 1. 前序遍历
     * 非递归
     * @param root 树根节点
     */
    public static void preorderTraversal2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 辅助栈
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) { // 不断将左子节点入栈，直到cur为空
                stack.push(cur);
                System.out.print(cur.val + "->"); // 前序遍历，先打印当前节点在打印左子节点，然后再把右子节点加到栈中
                cur = cur.left;
            }
            if (!stack.isEmpty()) { // 栈不为空，弹出栈元素
                cur = stack.pop(); // 此时弹出最左边的节点
                cur = cur.right; // 令当前节点为右子节点
            }
        }
    }

    /**
     * 1. 前序遍历
     * 非递归解法2
     * @param root 树根节点
     */
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 辅助栈保存树节点
        stack.add(root);
        while (!stack.isEmpty()) { // 栈不为空
            TreeNode temp = stack.pop();
            System.out.print(temp.val + "->"); // 先根节点，因为是前序遍历
            if (temp.right != null) { // 先添加右孩子，因为栈是先进后出
                stack.add(temp.right);
            }
            if (temp.left != null) {
                stack.add(temp.left);
            }
        }
    }
    
    /**
     * 2. 中序遍历
     * 递归
     * @param root 树根节点
     */
    public static void inorderTraversalRec(TreeNode root){
        if (root == null) {
            return;
        }
        inorderTraversalRec(root.left);
        System.out.print(root.val + "->");
        inorderTraversalRec(root.right);
    }
    /**
     * 2. 中序遍历
     * 非递归
     * @param root 树根节点
     */
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(); // 辅助栈
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) { // 不断将左子节点入栈，直到cur为空
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) { // 栈不为空，弹出栈元素
                cur = stack.pop(); // 此时弹出最左边的节点
                System.out.print(cur.val + "->"); // 中序遍历，先打印左子节点在打印当前节点，然后再把右子节点加到栈中
                cur = cur.right; // 令当前节点为右子节点
            }
        }
    }
    
    /**
     * 3. 后序遍历
     * 递归
     * @param root 树根节点
     */
    public static void postorderTraversalRec(TreeNode root){
        if (root == null) {
            return;
        }
        postorderTraversalRec(root.left);
        postorderTraversalRec(root.right);
        System.out.print(root.val + "->");
    }
    
    /**
     * 3. 后序遍历
     * 非递归
     * @param root 树根节点
     */
    public static void postorderTraversal(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<TreeNode>(); // 保存树节点
        Stack<TreeNode> stack2 = new Stack<TreeNode>(); // 保存后序遍历的结果
        stack1.add(root);
        while (!stack1.isEmpty()) {
            TreeNode temp = stack1.pop();
            stack2.push(temp); // 将弹出的元素加到stack2中
            if (temp.left != null) { // 左子节点先入栈
                stack1.push(temp.left);
            }
            if (temp.right != null) { // 右子节点后入栈
                stack1.push(temp.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + "->");
        }
    }
    
    /**
     * 4. 层次遍历
     * @param root 根节点
     */
    public static void levelTraversal(TreeNode root){
        if(root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // 对列保存树节点
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + "->");
            if (temp.left != null) { // 添加左右子节点到对列
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
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
        TreeUtil.preorderTraversalRec(t1);
        System.out.println();
        TreeUtil.preorderTraversal(t1);
        System.out.println();
        TreeUtil.preorderTraversal2(t1);
        System.out.println();
        TreeUtil.levelTraversal(t1);
    }

}
