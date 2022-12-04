package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.ListNode;

public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
