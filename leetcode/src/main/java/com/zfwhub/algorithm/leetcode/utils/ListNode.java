package com.zfwhub.algorithm.leetcode.utils;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    
    public int val;
    public ListNode next;
    
    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode [val=" + val + "]";
    }
    
    public String toLinkedString() {
        ListNode current = this;
        List<String> nodes = new ArrayList<>();
        while (current != null) {
            nodes.add(String.valueOf(current.val));
            current = current.next;
        }
        return String.join(",", nodes);
    }
    
    public static ListNode createLinkedList(int[] array) {
        ListNode head = null;
        ListNode current = null;
        for (int i = 0; i < array.length; i++) {
            if (head == null) {
                head = new ListNode(array[i]);
                current = head;
            } else {
                ListNode newNode  = new ListNode(array[i]);
                current.next = newNode;
                current = current.next;
            }
        }
        return head;
    }

}
