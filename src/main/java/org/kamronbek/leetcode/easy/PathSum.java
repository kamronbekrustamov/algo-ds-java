package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasSum(root, 0, targetSum);
    }

    private boolean hasSum(TreeNode node, int sum, int target) {
        if (node == null)
            return false;
        sum += node.val;

        if (sum == target && node.left == null && node.right == null)
            return true;

        return hasSum(node.left, sum, target) || hasSum(node.right, sum, target);
    }
}
