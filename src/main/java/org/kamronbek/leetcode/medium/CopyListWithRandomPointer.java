package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.RandomListNode;

public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode curr = head;
        RandomListNode temp;

        while(curr != null) {
            temp = curr.next;
            curr.next = new RandomListNode(curr.val);
            curr.next.next = temp;
            curr = temp;
        }

        curr = head;

        while (curr != null) {
            curr.next.random = curr.random == null ? null: curr.random.next;
            curr = curr.next.next;
        }

        RandomListNode newHead = head.next;
        curr = head;
        temp = newHead;

        while (curr.next.next != null) {
            curr.next = curr.next.next;
            temp.next = temp.next.next;
            curr = curr.next;
            temp = temp.next;
        }
        curr.next = null;

        return newHead;
    }
}
