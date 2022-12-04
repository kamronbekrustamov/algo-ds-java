package org.kamronbek.leetcode.medium;

import org.kamronbek.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    private List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> temp = new ArrayList<>();
        findSum(root, temp, 0, targetSum);
        return list;
    }

    private void findSum(TreeNode node, List<Integer> temp, int sum, int target) {
        if (node == null)
            return;
        sum += node.val;
        temp.add(node.val);

        if (sum == target && node.left == null && node.right == null) {
            list.add(new ArrayList<>());
            int last = list.size() - 1;
            for (int val: temp)
                list.get(last).add(val);
        }

        findSum(node.left, temp, sum, target);
        findSum(node.right, temp, sum, target);
        temp.remove(temp.size() - 1);
    }
}
