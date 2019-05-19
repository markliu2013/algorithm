package com.zfwhub.algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

import com.zfwhub.algorithm.leetcode.utils.ListNode;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNode {
    
    public static ListNode solution1(ListNode head, int n) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            nodeList.add(current);
            current = current.next;
        }
        if (nodeList.size() == 1) {
            return null;
        }
        // 删除的是第一个
        if (nodeList.size()-n-1 < 0) {
            head = head.next;
        } else {
            ListNode toRemovePre = nodeList.get(nodeList.size()-n-1);
            toRemovePre.next = toRemovePre.next.next;
        }
        return head;
    }
    
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/8804/Simple-Java-solution-in-one-pass
    
    public static void main(String[] args) {
        System.out.println(solution1(ListNode.createLinkedList(new int[] {1}), 1).toLinkedString());
//        System.out.println(ListNode.createLinkedList(new int[] {1,2,3}).toLinkedString());
    }
    
}
