package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

public class MaximumDepthOfBinaryTree {
    private int max = 0;
    public int maxDepth(TreeNode root) {
        findMax(root, 0);
        return max;
    }

    private void findMax(TreeNode node, int curDepth) {
        if (node == null)
            return;
        curDepth++;
        if (node.left == null && node.right == null && curDepth > max)
            max = curDepth;
        findMax(node.left, curDepth);
        findMax(node.right, curDepth);
    }
}
