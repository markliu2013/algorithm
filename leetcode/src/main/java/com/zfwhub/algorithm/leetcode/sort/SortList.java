package com.zfwhub.algorithm.leetcode.sort;

import java.math.BigInteger;

// https://leetcode.com/problems/sort-list/
public class SortList {
    
    // 选择排序，每次都从list中选出最小的数字。
    public static ListNode solution1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode currentNode = null;
        ListNode node = null;
        // 不断的在list中找
        while (head.next != null) {
            ListNode current = head;
            ListNode minNode = head;
            ListNode minNodePrev = null;
            if (head.next == null) {
                head = null;
                return minNode;
            }
            while (current.next != null) {
                ListNode prevNode = current;
                current = current.next;
                // 找到更小的节点。
                if (minNode.val > current.val) {
                    minNodePrev = prevNode;
                    minNode = current;
                }
            }
            // 将最小的节点删除
            if (minNodePrev != null) {
                minNodePrev.next = minNode.next;
            } else {
                head = minNode.next;
            }
            if (currentNode == null) {
                currentNode = new ListNode(minNode.val);
                node = currentNode;
            } else {
                currentNode.next = new ListNode(minNode.val);
                currentNode = currentNode.next;
            }
            minNode.next = null;
        }
        if (currentNode == null) {
            currentNode = new ListNode(head.val);
            node = currentNode;
        } else {
            currentNode.next = new ListNode(head.val);
            currentNode = currentNode.next;
        }
        return node;
    }
  
    // TODO SortList
    public static ListNode solution2(ListNode head) {
        return null;
    }
    
    private static ListNode parseIntegerToNode(BigInteger number) {
        String str = String.valueOf(number);
        ListNode current = new ListNode(Character.getNumericValue(str.charAt(str.length() - 1)));
        ListNode node = current;
        for (int i = 1; i < str.length(); i++) {
            current.next = new ListNode(Character.getNumericValue(str.charAt(str.length() - 1 - i)));
            current = current.next;
        }
        return node;
    }
    
    public static void main(String[] args) {
        ListNode head = parseIntegerToNode(new BigInteger("8762122"));
        ListNode node = solution1(head);
        while (node.next != null) {
            System.out.print(node.val);
            node = node.next;
        }
        System.out.println(node.val);
    }
    
}

class ListNode {
    
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
    
}