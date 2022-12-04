package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.Node;

public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if (root != null) {
            connectTree(root.left, root);
            connectTree(root.right, root);
        }
        return root;
    }

    public void connectTree(Node node, Node parent) {
        if (node == null)
            return;

        if (node == parent.left) {
            node.next = parent.right;
        }
        else if (parent.next != null) {
            node.next = parent.next.left;
        }

        connectTree(node.left, node);
        connectTree(node.right, node);
    }
}
