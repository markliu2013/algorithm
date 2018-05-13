package com.zfwhub.algorithm.leetcode.math;
import java.util.ArrayList;
import java.util.List;

/**
 * 小学的列竖式模拟加法
 * https://leetcode.com/problems/add-two-numbers/description/
 */
public class AddTwoNumbers {

    /**
     * 都转换为数字, 然后计算。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Long i1 = parseNodeToInter(l1);
        Long i2 = parseNodeToInter(l2);
        Long i3 = i1 + i2;
        return parseLongToNode(i3);
    }

    /**
     * 列竖式模拟
     */
    // TODO
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return null;
    }

    public Long parseNodeToInter(ListNode node) {
        String linkedString = parseNodetoLinkedString(node);
        StringBuilder linkedStringBuilder = new StringBuilder(linkedString.replaceAll(",", ""));
        String integerString = linkedStringBuilder.reverse().toString();
        return new Long(integerString);
    }
    public ListNode parseLongToNode(Long number) {
        String str = String.valueOf(number);
        ListNode current = new ListNode(Character.getNumericValue(str.charAt(str.length()-1)));
        ListNode node = current;
        for (int i = 1; i < str.length(); i++) {
            current.next = new ListNode(Character.getNumericValue(str.charAt(str.length()-1-i)));
            current = current.next;
        }
        return node;
    }

    public String parseNodetoLinkedString(ListNode node) {
        ListNode current = node;
        List<String> nodes = new ArrayList<String>();
        while (current != null) {
            nodes.add(String.valueOf(current.val));
            current = current.next;
        }
        return String.join(",", nodes);
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
