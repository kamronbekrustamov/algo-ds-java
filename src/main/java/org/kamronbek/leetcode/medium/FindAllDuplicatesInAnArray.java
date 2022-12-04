package org.kamronbek.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[(nums[i] - 1) % length] += length;
        }

        for (int i = 0; i < length; i++) {
            int count = (nums[i] - 1) / length;
            if (count > 1)
                result.add(i + 1);
        }
        return result;
    }
}