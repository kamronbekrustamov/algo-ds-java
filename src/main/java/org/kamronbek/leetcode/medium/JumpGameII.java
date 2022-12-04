package org.kamronbek.leetcode.medium;

public class JumpGameII {

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int lastIndex = nums.length - 1;
        int curMax = nums[0];
        int nextMax = 0;
        int steps = 1;
        for (int i = 1; i < lastIndex; i++) {
            if (i + nums[i] > nextMax) {
                nextMax = i + nums[i];
            }
            if (curMax == i) {
                steps++;
                curMax = nextMax;
            }
            if (curMax >= lastIndex)
                break;
        }
        return steps;
    }
}
