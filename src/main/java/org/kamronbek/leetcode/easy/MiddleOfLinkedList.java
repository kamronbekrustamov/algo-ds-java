package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.ListNode;

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        while(second.next != null) {
            first = first.next;
            second = second.next;
            if (second.next != null)
                second = second.next;
        }
        return first;
    }
}
