package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

public class MinimumDepthOfBinaryTree {
    private int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        findMin(root, 0);
        return min;
    }

    private void findMin(TreeNode node, int curDepth) {
        if (node == null)
            return;
        curDepth++;

        if (node.left == null && node.right == null && curDepth < min)
            min = curDepth;

        findMin(node.left, curDepth);
        findMin(node.right, curDepth);
    }
}
