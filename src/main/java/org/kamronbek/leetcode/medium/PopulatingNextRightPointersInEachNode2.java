package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.Node;

public class PopulatingNextRightPointersInEachNode2 {

    public Node connect(Node root) {
        if (root != null) {
            connectTree(root.right, root);
            connectTree(root.left, root);
        }
        return root;
    }

    public void connectTree(Node node, Node parent) {
        if (node == null)
            return;

        if (node == parent.left && parent.right != null) {
            node.next = parent.right;
        } else {
            node.next = findRightNode(parent.next);
        }

        connectTree(node.left, node);
        connectTree(node.right, node);
    }

    public Node findRightNode(Node node) {
        while (node != null) {
            if (node.left != null)
                return node.left;
            if (node.right != null)
                return node.right;
            node = node.next;
        }

        return null;
    }
}
