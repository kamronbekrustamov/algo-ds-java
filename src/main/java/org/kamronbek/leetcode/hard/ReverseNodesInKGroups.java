package org.kamronbek.leetcode.hard;

import org.kamronbek.leetcode.utils.ListNode;

public class ReverseNodesInKGroups {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1)
            return head;
        int size = 0;
        ListNode temp = head;
        while(temp != null) {
            size++;
            temp = temp.next;
        }
        if (k > size)
            return head;
        ListNode prev = null;
        ListNode curr = null;
        ListNode next = head;
        ListNode tail1 = head;
        ListNode tail2 = null;
        ListNode newHead = null;

        for (int i = 0; i < size / k; i++) {
            for (int j = 0; j < k; j++) {
                prev = curr;
                curr = next;
                next = next.next;
                curr.next = prev;    
            }
            if (i == 0) {
                newHead = curr;
                tail2 = next;
            } else {
                tail1.next = curr;
                tail1 = tail2;
                tail2 = next;
            }
        }
        tail1.next = tail2;
        return newHead;
    }
}