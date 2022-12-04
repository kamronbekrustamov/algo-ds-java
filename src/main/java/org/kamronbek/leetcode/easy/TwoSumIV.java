package org.kamronbek.leetcode.easy;

import org.kamronbek.leetcode.utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    public boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(k - root.val);
        return find(root.right, k, set) || find(root.left, k, set);
    }
}