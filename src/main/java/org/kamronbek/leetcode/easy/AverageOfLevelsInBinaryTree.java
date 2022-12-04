package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count;
        long sum;
        int size;

        while (!queue.isEmpty()) {
            count = 0;
            sum = 0;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                sum += node.val;
                count++;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(sum * 1.0 / count);
        }
        return result;
    }

}
