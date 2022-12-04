package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        findLeafValues(root1, list1);
        findLeafValues(root2, list2);
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i)))
                return false;
        }
        return true;
    }

    public void findLeafValues(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            list.add(node.val);
        findLeafValues(node.left, list);
        findLeafValues(node.right, list);
    }

}
