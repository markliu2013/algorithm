package com.zfwhub.algorithm.leetcode.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNumbers {

    /**
     * convert to number, then add。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger i1 = parseNodeToInter(l1);
        BigInteger i2 = parseNodeToInter(l2);
        BigInteger i3 = i1.add(i2);
        return parseIntegerToNode(i3);
    }

    /**
     * like how you would sum two numbers on a piece of paper
     * http://www.aaamath.com/add27dx1.htm
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode currentNode1 = l1;
        ListNode currentNode2 = l2;
        int remainVal = 0;
        int currentVal = currentNode1.val + currentNode2.val;
        if (currentVal > 9) {
            currentVal = currentVal - 10;
            remainVal = 1;
        }
        result = new ListNode(currentVal);
        ListNode l3 = null;
        if (remainVal != 0) {
            l3 = new ListNode(remainVal);
            result.next = l3;
        }
        ListNode currentNode = result;
        while (currentNode1.next != null || currentNode2.next != null) {
            int currentVal1 = 0;
            int currentVal2 = 0;
            if (currentNode1.next != null) {
                currentNode1 = currentNode1.next;
                currentVal1 = currentNode1.val;
            }
            if (currentNode2.next != null) {
                currentNode2 = currentNode2.next;
                currentVal2 = currentNode2.val;
            }
            currentVal = remainVal + currentVal1 + currentVal2;
            if (currentVal > 9) {
                currentVal = currentVal - 10;
                remainVal = 1;
            } else {
                remainVal = 0;
            }
            ListNode l4 = new ListNode(currentVal);
            currentNode.next = l4;
            currentNode = currentNode.next;
            if (remainVal != 0) {
                l4.next = new ListNode(remainVal);
            }
        }
        return result;
    }
    
    /**
     * https://leetcode.com/problems/add-two-numbers/solution/
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public BigInteger parseNodeToInter(ListNode node) {
        String linkedString = parseNodetoLinkedString(node);
        StringBuilder linkedStringBuilder = new StringBuilder(linkedString.replaceAll(",", ""));
        String integerString = linkedStringBuilder.reverse().toString();
        return new BigInteger(integerString);
    }

    public ListNode parseIntegerToNode(BigInteger number) {
        String str = String.valueOf(number);
        ListNode current = new ListNode(Character.getNumericValue(str.charAt(str.length() - 1)));
        ListNode node = current;
        for (int i = 1; i < str.length(); i++) {
            current.next = new ListNode(Character.getNumericValue(str.charAt(str.length() - 1 - i)));
            current = current.next;
        }
        return node;
    }

    public static String parseNodetoLinkedString(ListNode node) {
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
        ListNode(int x) {
            val = x;
        }
    }
    
    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = addTwoNumbers.parseIntegerToNode(new BigInteger("342"));
        ListNode l2 = addTwoNumbers.parseIntegerToNode(new BigInteger("465"));
        System.out.println(parseNodetoLinkedString(addTwoNumbers.addTwoNumbers3(l1, l2)));
        System.out.println(parseNodetoLinkedString(addTwoNumbers.addTwoNumbers2(l1, l2)));
    }

}
