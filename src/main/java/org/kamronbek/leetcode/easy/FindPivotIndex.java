package org.kamronbek.leetcode.easy;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int leftSum = 0, rightSum = 0;
        int pivotIndex = -1;

        for (int num : nums) leftSum += num;

        for (int i = nums.length - 1; i >= 0; i--) {
            leftSum -= nums[i];
            if (leftSum == rightSum)
                pivotIndex = i;
            rightSum += nums[i];
        }

        return pivotIndex;
    }
}
