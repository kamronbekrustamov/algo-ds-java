package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.ListNode;

public class ReverseLinkedList {

    private ListNode head = null;

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = null;
        ListNode next = head;
        while(next != null) {
            prev = curr;
            curr = next;
            next = next.next;
            curr.next = prev;
        }
        return curr;

        // Reversing Recursevily
        // reverseRecursively(null, head);
        // return this.head;
    }
    
    public void reverseRecursively(ListNode prev, ListNode curr) {
        if (curr == null) {
            head = prev;
            return;
        }

        reverseRecursively(curr, curr.next);
        curr.next = prev;
    }
}
