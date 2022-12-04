package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

public class MinimumDistanceBetweenBSTNodes {
    private Integer prev = null;
    private int minDis = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        findMin(root);
        return minDis;
    }

    public void findMin(TreeNode node) {
        if (node == null)
            return;

        findMin(node.left);

        if (prev != null)
            minDis = Math.min(minDis, node.val - prev);
        prev = node.val;

        findMin(node.right);
    }
}
