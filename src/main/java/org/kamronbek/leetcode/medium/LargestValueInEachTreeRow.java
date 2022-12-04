package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max;
        int size;

        while (!queue.isEmpty()) {
            max = Integer.MIN_VALUE;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (node.val > max)
                    max = node.val;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(max);
        }
        return result;
    }

}
