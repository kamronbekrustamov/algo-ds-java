package org.kamronbek.leetcode.easy;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] totalCosts = new int[len];
        totalCosts[len - 1] = cost[len - 1];
        totalCosts[len - 2] = cost[len - 2];

        for (int i = len - 3; i >= 0; i--)
            totalCosts[i] = cost[i] + Math.min(totalCosts[i + 1], totalCosts[i + 2]);

        return Math.min(totalCosts[0], totalCosts[1]);
    }
}
