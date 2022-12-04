package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.ListNode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        int carry = 0;
        int val = 0;
        while(l1 != null && l2 != null) {
            val = l1.val + l2.val + carry;
            if (val >= 10) {
                val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            if (current == null) {
                head = new ListNode(val, null);
                current = head;
            } else {
                current.next = new ListNode(val, null);
                current = current.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            val = l1.val + carry;
            if (val >= 10) {
                val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            current.next = new ListNode(val, null);
            current = current.next;
            l1 = l1.next;
        }

        while(l2 != null) {
            val = l2.val + carry;
            if (val >= 10) {
                val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            current.next = new ListNode(val, null);
            current = current.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            current.next = new ListNode(1, null);
        }

        return head;
    }
}